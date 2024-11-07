<template>
    <div class="container mt-3">
    <h1 class="display-1 text-center">사용자 등록</h1>
    <form>
      <div class="mb-3 mt-3">
        <label for="name" class="form-label">이름:</label>
        <input type="text" class="form-control" id="name" placeholder="이름을 입력하세요." name="name" autocomplete="off" v-model="result.name"
        :class="{active:active.name}"/>
      </div>
      <div class="mb-3 mt-3">
        <label for="email" class="form-label">이메일:</label>
        <input type="email" class="form-control" id="email" placeholder="이메일를 입력하세요." name="email" autocomplete="off" v-model="result.email"
        :class="{active:active.email}"/>
      </div>
      <div class="mb-3">
        <label for="pwd" class="form-label">비밀번호:</label>
        <input type="password" class="form-control" id="pwd" placeholder="비밀번호를 입력하세요." name="pwd" autocomplete="off" v-model="result.pwd"
        :class="{active:active.pwd}"/>
      </div>
      <div class="d-flex">
        <div class="p-2 flex-fill">
          <div class="form-check">
            <input type="radio" class="form-check-input" id="radio1" name="gender" v-bind:value="'M'" v-model="result.gender"/>
            <label class="form-check-label" for="radio1">M</label>
          </div>
        </div>
        <div class="p-2 flex-fill">
          <div class="form-check">
            <input type="radio" class="form-check-input" id="radio2" name="gender" v-bind:value="'F'" v-model="result.gender"/>
            <label class="form-check-label" for="radio2">F</label>
          </div>
        </div>
      </div>
    </form>
    <div class="d-flex">
      <div class="p-2 flex-fill d-grid">
        <button type="button" class="btn btn-danger" @click="save">생성</button>
      </div>
      <div class="p-2 flex-fill d-grid">
        <router-link to="/user" class="btn btn-danger">취소</router-link>
      </div>
    </div>
  </div>
</template>

<script>
// store
// 애플리케이션의 여러 컴포넌트에서 공유해야 하는 데이터를 한 곳에 모아 관리할 수 있도록 가능하게 함.(vuex를 통해서)
// 1.State: 애플리케이션의 전역 상태 데이터가 저장되는 곳
// 2.Getters: state의 값을 계산하거나 가공하여 반환하는 속성으로, 마치 컴포넌트의 computed 속성처럼 작동함.
// 3.Mutations: state를 변경할 수 있는 메서드들. Vuex에서는 state를 직접 수정할 수 없기 때문에 반드시 mutations를 통해 변경해야 함.
// 4.Actions: 비동기 작업을 포함한 로직을 처리하는 곳으로, API 호출이나 타이머와 같은 작업을 수행할 때 사용함. mutations와는 달리, actions는 비동기적으로 동작할 수 있음.
// 5.Modules: store를 여러 모듈로 나눠서 관리할 수 있도록 해주는 기능. 이를 통해 복잡한 애플리케이션에서도 store를 체계적으로 구성할 수 있음.
import axios from 'axios';
export default {
  name:'CreateVeiw',
  data(){
    return {
      result:{name:'',email:'',pwd:'',gender:'M', refDate: new Date().toISOString()},
      active:{name:false,email:false,pwd:false}
    }
  },
  created(){},

  methods: {
    save() {
        console.log(this.result)
        if(this.result.name == ''){
          console.log('사용자 이름을 입력해주세요')
          this.active.name =true
          return
        } else {
          this.active.name =false
        }

        if(this.result.email == ''){
          console.log('사용자 메일을 입력해주세요')
          this.active.email =true
          return
        }else {
          this.active.email =false
        }

        if(this.result.pwd == ''){
          console.log('사용자 비밀번호를 입력해주세요')
          this.active.pwd =true
          return
        }else {
          this.active.pwd =false
        }
        console.log(this.result)

        axios.put('http://localhost:8080/save', this.result)
          .then((res) => {
            console.log("응답 데이터:", res.data); // 응답 데이터 구조를 확인
            if (res.data.state) {
              console.log('사용자 생성이 성공하였습니다');
              if (res.data.result) {
                console.log('결과:', res.data.result); // 서버에서 반환된 실제 result 확인
              } else {
                console.log('결과가 null입니다');
              }
    }
  })
  .catch((err) => {
    console.log('오류발생', err.response.data);
  });
    }
  }
    
}
</script>

<style>
.active{
  background-color: blueviolet;
}
</style>