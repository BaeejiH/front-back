<template>
 <div class="container mt-3">
    <h1 class="display-1 text-center">사용자 수정</h1>
    <form>
      <div class="mb-3 mt-3">
        <label for="name" class="form-label">이름:</label>
        <input type="text" class="form-control" id="name" placeholder="이름을 입력하세요." name="name" autocomplete="off" v-model="result.name"/>
      </div>
      <div class="mb-3 mt-3">
        <label for="email" class="form-label">이메일:</label>
        <input type="email" class="form-control" id="email" placeholder="이메일를 입력하세요." name="email" autocomplete="off" readonly v-model="result.email"/>
      </div>
      <div class="mb-3">
        <label for="pwd" class="form-label">비밀번호:</label>
        <input type="password" class="form-control" id="pwd" placeholder="비밀번호를 입력하세요." name="pwd" autocomplete="off" v-model="result.pwd"/>
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
        <button type="submit" class="btn btn-danger" @click="save">저장</button>
      </div>
      <div class="p-2 flex-fill d-grid">
        <button type="button" class="btn btn-danger" @click="cancel">취소</button>
      </div>
    </div>
  </div>
</template>

<script>
 import axios from 'axios';
import SelectView from './SelectView.vue';
  export default {
  name: 'UpdateView',
  data() {
    return {
      result: {}
    };
  },
  created() {
      this.result = this.$store.state.user 
  },
  methods: {
    save() {
      axios.post('http://localhost:8080/editById', this.result)
        .then((res) => {
          console.log(res);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    cancel() {
      // 취소 버튼 클릭 시 할 작업
      this.$router.push({ name: 'SelectView', params: { name: this.result.name } })
      // router에 경로에 name이라는 매개변수를 넣어놔서 취소를 통해 돌아갈때도 매개변수 넣어줘야함.
    }
  }
}
</script>

<style>

</style>