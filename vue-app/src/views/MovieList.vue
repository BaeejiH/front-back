<template>
    <div class="container mt-3">
        <h1 class="display-1 text-center">영화 목록</h1>
        <table class="table table-hover mt-3">
            <thead class="table-dark">
                <tr>
                    <th>번호</th>
                    <th>장르</th>
                    <th>제목</th>
                    <th>감독</th>
                    <th>배급사</th>
                    <th>개봉일</th>
                    <th>상영시간</th>
                    <th>줄거리</th>
                    <th>bcode</th>
                </tr>
            </thead>
            <tbody>
                <tr class="cursor-pointer" v-for="row in result" :key="row.movie_num" v-on:click="$event => href(row)">
                    <td>{{ row.movie_num }}</td>
                    <td>{{ row.genre }}</td>
                    <td>{{ row.title }}</td>
                    <td>{{ row.directore }}</td>
                    <td>{{ row.producer }}</td>
                    <td>{{ row.release_date }}</td>
                    <td>{{ row.runtime }}</td>
                    <td>
                        <p>{{ des(row.description) }}</p>
                        <button v-if="row.description.length > 100" @click="tdes(row)">더 보기</button>
                    </td>
                    <td>{{ row.bcode }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import axios from 'axios';
import store from '@/store';
export default {
    name: 'MovieList',
    data() {
        return {
            result: []
        }
    },
    created() {
        this.getData()
        console.log(store)
    },
    methods: {
        getData() {
            axios
                .get('http://localhost:8080/selectMovie')
                .then((response) => {
                    console.log('aaaa',response)
                    this.result = response.data.result

                    let resultTmp = {no: 0}
                   console.log('aaaaaa',resultTmp)

                   let pp = {resultTmp,...this.result} // ### 1.객체 스프레드 ###  2. map  3. filter
                   console.log('pppppp',pp)


                    this.result.unshift(resultTmp)
                    console.log(this.result.length)

                    // 장르가 Drama 일때 드라마로 변경
                    // for(let i=0; i<this.result.length; i++){
                    //     if(this.result[i].genre === 'Drama')
                    //     this.result[i].genre ='드라마'
                    // }
                    
                    // 첫 번째 러일
                //     for(let i=0; i<this.result.length; i++){
                //          console.log('yyyy', this.result[i].genre.indexOf('D')) 
                //     if(this.result[i].genre.indexOf('D') === 0){
                //         // console.log('zzzz', this.result[i].genre.str[0])

                //         // 첫 번째 문자열이이 D일 경우 d로변경함.(replace!!)
                //         this.result[i].genre =this.result[i].genre.replace('D','d');
                //     }            
                //  }

                // 상영시간이 130분 이하면 xx 표시로 변경
                // for(let i=0; i<this.result.length; i++){
                //     if(this.result[i].runtime <=130){
                //         this.result[i].runtime = '-'
                //         const fResult = this.result.filter(movie => movie.runtime > 130);
                //         this.result = fResult
                //     }
                // }



                })



                .catch((error) => {
                    console.log(error)
                })
        },

        href(row) {
            console.log(row)
            store.commit('setMovie', row)
            this.$router.push({ name: 'DetailMovie', params: row })
        },

        des(description) {
            if (description.length > 50) {
                return description.slice(0, 50) + '...';
            }
            return description;
        },

       
        tdes(row) {
            row.showFullDescription = !row.showFullDescription;
        },
    }
}

</script>

<style scoped>
.container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
}

.display-1 {
    font-size: 3rem;
    font-weight: bold;
    color: #333;
    margin-bottom: 30px;
}

.table {
    width: 100%;
    border-collapse: collapse;
    border-radius: 8px;
    overflow: hidden;
    background-color: #fff;
}

.table-dark {
    background-color: #343a40;
    color: white;
}

.table th,
.table td {
    padding: 15px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}

.table th {
    font-weight: bold;
    color: #f1f1f1;
}

.table td {
    color: #555;
    font-size: 1rem;
}

.table-hover tr:hover {
    background-color: #f1f1f1;
    cursor: pointer;
}

.cursor-pointer {
    transition: background-color 0.3s ease;
}

.cursor-pointer:hover {
    background-color: #f1f1f1;
}

.mt-3 {
    margin-top: 1rem;
}
</style>