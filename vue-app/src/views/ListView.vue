<template>
   <div class="container mt-3">
    <h1 class="display-1 text-center">사용자 목록</h1>
    <div class="btn-group">
      <router-link to="/user/save" class="btn btn-primary">사용자 추가</router-link>
      <router-link to="/" class="btn btn-danger">메인화면으로돌아가기</router-link>
    </div>
    <table class="table table-hover mt-3">
      <thead class="table-dark">
        <tr>
          <th>이름</th>
          <th>이메일</th>
          <th>가입날짜</th>
        </tr>
      </thead>
      <tbody>
        <tr 
          class="cursor-pointer" 
          v-for="row in result" 
          :key="row.no" 
          v-on:click ="$event => href(row)"
        >
          <td>{{ row.name }}</td>
          <td>{{ row.email }}</td>
          <td>{{ row.refDate }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';
import store from '@/store'
export default {
  name: 'ListView',
  data(){
    return {
      result : []
    }
  },
  created(){
    this.getData()
    console.log(store)
  },
  methods :{
    getData() {
      axios
      .post('http://localhost:8080/findAll')
      .then((response)=>{
        console.log(response)
        this.result = response.data.result
    })
      .catch((error)=>{
        console.log(error)
    })
    },
    href(row) {
      console.log(row)
      store.commit('setUser',row)
      this.$router.push({name: 'SelectView', params: row}) 
      // a태그 같은 역할  
      // query : 데이터를 넘기는 역할, 단 href뒤에 데이터가 그대로 노출됨.

      // router의 속성값을 이용해서 데이터를 전달하는 방법
      // quert > http://localhost:8080/user/findById/?name=사용자&pwd=1
      // params >http://localhost:8080/user/findById/사용자/1
      // index.js > path: '/user/findById/:name/:pwd'
    },

    
  }
}
</script>

<style>

</style>