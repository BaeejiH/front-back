<template>
    <div class="container mt-3">
    <h1 class="display-1 text-center">사용자 정보</h1>
    <form>
      <div class="mb-3 mt-3">
        <label for="name" class="form-label">이름:</label>
        <input type="text" class="form-control" id="name" placeholder="이름을 입력하세요." name="name" readonly v-model="result.name" />
      </div>
      <div class="mb-3 mt-3">
        <label for="email" class="form-label">이메일:</label>
        <input type="email" class="form-control" id="email" placeholder="이메일를 입력하세요." name="email" readonly v-model="result.email" />
      </div>
      <div class="mb-3">
        <label for="pwd" class="form-label">비밀번호:</label>
        <input type="password" class="form-control" id="pwd" placeholder="비밀번호를 입력하세요." name="pwd" readonly v-model="result.pwd" />
      </div>

      <div class="d-flex">
        <div class="p-2 flex-fill">
          <div class="form-check">
            <input
              type="radio"
              class="form-check-input"
              id="radio1"
              name="gender"
              v-bind:value="'M'"
              v-model="result.gender"
            />
            <label class="form-check-label" for="radio1">M</label>
          </div>
        </div>

        <div class="p-2 flex-fill">
          <div class="form-check">
            <input
              type="radio"
              class="form-check-input"
              id="radio2"
              name="gender"
              v-bind:value="'F'"
              v-model="result.gender"
            />
            <label class="form-check-label" for="radio2">F</label>
          </div>
        </div>
      </div>
    </form>
    <div class="d-flex">
      <div class="p-2 flex-fill d-grid">
        <button type="button" class="btn btn-danger" @click="edit">수정</button>
      </div>
      <div class="p-2 flex-fill d-grid">
        <button class="btn btn-danger" @click="del">삭제</button>
      </div>
      <div class="p-2 flex-fill d-grid">
        <button class="btn btn-danger" @click="cancel">취소</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name:'Selectview',
  data(){
    return {
      result:{}
    }
  },
  created(){
    this.result = this.$store.state.user;
    console.log("User data:", this.result)
  },
  methods: {
    edit(){
       this.$router.push({name:'UpdateView',params:{no:this.result.no}})
    },
    del(){
      const params = {params:{no:this.result.no}} // 삭제버튼 클릭시  404 error (AxiosError: Request failed with status code 404)
      // `VUE_APP_BASEURL`이 올바르게 설정되어 있는지 확인
      console.log("Base URL:", process.env.VUE_APP_BASEURL); // 환경변수 설정이 안되있어서 404 error
      console.log("User ID:", this.result.no);
      if (!this.result.no) {
        console.error("User ID is undefined.");
        return;
      }

      axios.delete('http://localhost:8080/delete',params) //ListView에서 썻던 주소방식으로 삭제로직을 실행.
        .then((res) => {
        console.log(res);
        if (res.data.state) {
          alert(res.data.message);
          this.$store.commit('setUser', {});  // Vuex에서 데이터 초기화
          this.$router.push({ name: 'ListView' });  // ListView로 돌아가기
        } else {
          alert(res.data.message);
        }
      })
        .catch((err) => console.log(err))

    },
    cancel(){
      this.$store.commit('setUser',{})
      this.$router.push({name:'ListView'})
    }
  }
}
</script>

<style>

</style>