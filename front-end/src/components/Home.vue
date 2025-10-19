<template>
  <div class="home-container">
    <div class="nav-container">
      <Navbar></Navbar>
    </div>
    <div class="top-container">
        <el-button type="primary" @click="showAddDialog">添加学生</el-button>
    </div>

     <el-dialog v-model="dialogFormVisible" title="添加学生" width="500">
    <el-form :data="form" ref="formRef">
      <el-form-item label="学号" :label-width="formLabelWidth"> </el-form-item>
        <el-input v-model="form.id" autocomplete="off" />
        <el-form-item label="年龄" :label-width="formLabelWidth"> </el-form-item>
        <el-input v-model="form.age" autocomplete="off" />
        <el-form-item label="姓名" :label-width="formLabelWidth"> </el-form-item>
        <el-input v-model="form.name" autocomplete="off" />
        <el-form-item label="专业" :label-width="formLabelWidth"> </el-form-item>
        <el-input v-model="form.major" autocomplete="off" />
        <el-form-item label="性别" :label-width="formLabelWidth"> </el-form-item>
        <el-input v-model="form.gender" autocomplete="off" />
        </el-form>
        <template #footer>
          <div class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取消</el-button>
            <el-button type="primary" @click="addStudent" :loading="adding">
              确定
            </el-button>
          </div>
        </template>
         </el-dialog>
               <el-dialog v-model="editdialogVisible" title="修改学生" width="500">
             <el-form :model="editForm" ref="editFormRef">
        <el-form-item label="学号" :label-width="formLabelWidth">
          <el-input v-model="editForm.id" autocomplete="off" readonly />
        </el-form-item>
        <el-form-item label="年龄" :label-width="formLabelWidth">
          <el-input v-model="editForm.age" autocomplete="off" />
        </el-form-item>
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="editForm.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="专业" :label-width="formLabelWidth">
          <el-input v-model="editForm.major" autocomplete="off" />
        </el-form-item>
        <el-form-item label="性别" :label-width="formLabelWidth">
          <el-input v-model="editForm.gender" autocomplete="off" />
        </el-form-item>
      </el-form>
        <template #footer>
          <div class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取消</el-button>
            <el-button type="primary" @click="updateStudent" :loading="adding">
              确定
            </el-button>
          </div>
        </template>
        </el-dialog>
 

    <div class="content-container">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="id" label="学号" width="180" />
        <el-table-column prop="age" label="年龄" width="180" />
        <el-table-column prop="name" label="姓名" />
        <el-table-column prop="major" label="专业" />
        <el-table-column prop="gender" label="性别" />
            <el-table-column label="Operations">
      <template #default="scope">
        <el-button
          size="small"
          type="danger"
          @click="handleDelete(scope.row.id)"
        >
          删除
        </el-button>
        <el-button
          size="small"
          type="primary"
          @click="handleEdit(scope.row)"
        >
          编辑
        </el-button>
      </template>
    </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script lang="ts" setup>
import Navbar from './Navbar.vue'
import{ref,onMounted,reactive} from 'vue'
import axios from 'axios'
import {ElMessage} from 'element-plus'
import type{FormInstance} from 'element-plus'
interface Student{
    id:number,
    age:number,
    name:string,
    major:string,
    gender:string,
}
const form=reactive({
    id:'',
    age:'',
    name:'',
    major:'',
    gender:'',
})
const editForm=reactive({
    id:'',
    age:'',
    name:'',
    major:'',
    gender:'',
})
const loading=ref(false)
const tableData =ref<Student[]>([])
const dialogFormVisible=ref(false)
const editdialogVisible=ref(false)
const formLabelWidth='80px'
const adding=ref(false)
const formRef=ref<FormInstance>()
const editFormRef=ref<FormInstance>()
//显示添加弹窗
const showAddDialog=()=>{
    dialogFormVisible.value=true
}


const fetchData=async()=>{
     try {
    const response = await axios.get('http://localhost:8080/list')
    tableData.value = response.data
    ElMessage.success('数据获取成功')
  } catch (error) {
    console.error('获取数据失败:', error)
    ElMessage.error('获取数据失败，请检查服务器连接')
  } finally {
    loading.value = false
  }
}
const deleteData=async(id:number)=>{
    await axios.delete(`http://localhost:8080/delete?id=${id}`)
}
//添加学生
const  addStudent=async()=>{
    dialogFormVisible.value=true 
    await axios.post('http://localhost:8080/add',{
        id:form.id,
        age:form.age,
        name:form.name,
        major:form.major,
        gender:form.gender,

    })
    ElMessage.success('添加成功')
    dialogFormVisible.value=false
    await fetchData()
}
const handleDelete=async(id:number)=>{
    try{
        await deleteData(id)
    }
    catch(error){
        ElMessage.error('删除失败，请检查服务器连接')
    }
    tableData.value=tableData.value.filter(item=>item.id!==id)
    ElMessage.success('删除成功')
}
//处理编辑信息逻辑
const handleEdit=(student:Student)=>{
       // 将当前行的数据复制到编辑表单
    Object.assign(editForm, {
        id: student.id,
        age: student.age,
        name: student.name,
        major: student.major,
        gender: student.gender,
    })
    editdialogVisible.value = true
}
//更新信息
const updateStudent=async()=>{
    editdialogVisible.value=true 
    await axios.patch('http://localhost:8080/update',{
        id:editForm.id,
        age:editForm.age,
        name:editForm.name,
        major:editForm.major,
        gender:editForm.gender,
    })
    ElMessage.success('更新成功')
    editdialogVisible.value=false
    await fetchData()
}
onMounted(()=>{
    fetchData()
})
</script>

<style scoped>
.home-container {
  display: flex;
  height: 100vh;
  width: 100%;
}

.nav-container {
  /* 左侧导航栏固定宽度 */
  width: 300px;
  background-color: #f5f5f5;
  border-right: 1px solid #e0e0e0;
}
.top-container{
    padding: 20px;
}
.content-container {
  flex: 1; /* 右侧内容区域自适应 */
  padding: 20px;
  overflow: auto; /* 内容过多时显示滚动条 */
}
</style>