<template>
  <el-row class="tac">
    <el-col :span="12">
      <h5>学生信息管理系统</h5>
      <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
      >
        <el-sub-menu index="1">
          <template #title>
            <el-icon><location /></el-icon>
            <span @click="handleClick">学生信息</span>
          </template>    
        </el-sub-menu>
        <el-menu-item index="2">
          <el-icon><icon-menu /></el-icon>
          <span>个人信息</span>
        </el-menu-item>
      </el-menu>
    </el-col>
    
  </el-row>
</template>

<script lang="ts" setup>
import{useRouter} from 'vue-router'
const router=useRouter()

import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'

const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}

const handleClick=()=>{
  router.push({name:'student'})
}

</script>