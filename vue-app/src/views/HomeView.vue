<template>
  <div class="home">
    <router-link to="/user">사용자목록</router-link>
    <button @click="getData">데이터 호출</button>
  </div>
</template>

<script>
import axios from 'axios';

//HomeView.vue:27   
//@RequestParam 과 @RequestBody 의 데이터 보내는 방식이 다름.
//DELETE http://localhost:8080/delete 400 (Bad Request) --> RequestParam 어노테이션을 사용했기 때문에 데이터 포맷을 아래와 같이하면 오류가 남.
const uForm ={
  no:1,
  name:'Alice',
  email:'alice@example.com',
  pwd:'password123',
  gender:'F',
  del: 0, 
  refDate: new Date().toISOString() // dateTime type 불일치로 인한 파싱 불가. 옆에 처럼 써줘야 가능.
}
// 아래와 같은 형식으로 값을 넘겨줘야 삭제가 성공함.
const params ={
  'params':{no:1}
}
export default {
  // CORS(Cross-Origin Resource Sharing)
  // 특정 교차 도메인 간 요청, 특히 ajax요청은 동일-출처 보안정책에 의해 기본적을 금지
  // Front-End 와 Back-End 사이 axios를 이용해 데이터를 불러 올때 local주소가 다르면 오류가 남
  // 허용방법
  // 1. 리소스 서비스 서버에서 특정 교차 도메인 등록  2. 요청하는 도메인 서버쪽 Proxy 설정으로 우회 접속
  name: 'HomeView',
  methods : {
    getData(){
    axios.put('http://localhost:8080/save',uForm) // axios로 전송시 Data 준비중이라는 문구가 뜸. 자바쪽에서 보내는 데이터. get 안에 '/'로 전송시 index.html이 넘어감. 
    .then((response)=>{
      console.log(response)
    })
    .catch((error)=>{
      console.log(error)
    })
    }
  }
}
</script>

<style scoped>
a {
  color: black;
  text-decoration: none;
  font-weight: bold;
  font-size: 1.5rem;
}
</style>
