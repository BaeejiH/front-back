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
                    <th>평점</th>
                </tr>
            </thead>
            <tbody>
                <tr class="cursor-pointer" v-for="row in result" :key="row.movie_num" v-on:click="$event => href(row)">
                    <td>{{ row.movie_num }}</td>
                    <td>{{ row.genre }}</td>
                    <td style="color: blueviolet;">{{ row.title }}★{{ row.title.length }}★</td>
                    <td>{{ row.directore }}</td>
                    <td>{{ row.producer }}</td>
                    <td>{{ row.release_date }}</td>
                    <td>{{ row.runtime }}</td>
                    <td>
                        <p v-if="row.description">{{ des(row.description) }}</p>
                        <button v-if="row.description && row.description.length > 100" @click="tdes(row)">더 보기</button>
                    </td>
                    <td>{{ row.bcode }}</td>
                    <td>{{ rate(row) }}</td>
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
                    console.log('aaaa', response)
                    this.result = response.data.result
                    console.log('xxxxxx')

                    //중복제거
                    // include
                    this.result = this.result.filter((item, index, self) =>
                        index === self.findIndex((t) => t.title === item.title)
                    );

                    console.log('중복제거:'+this.result)
              

                    console.log(this.result);

                    // 데이터값이 없는행을 추가하려다보니 Cannot read properties of undefined (reading 'length') 오류, 조건문 추가
                    // this.result.unshift({
                    //     movie_num: 0
                    //     , genre: '호러'
                    //     , title: '컨저링'
                    //     , directore: '가나다'
                    //     , producer: '(주)가나다'
                    //     , release_date: '2014-10-10'
                    //     , runtime: '333'
                    //     , description: '가나다라마바사'
                    //     , bcode: 777
                    // })
                    // console.log('bbbbbb',)



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

                    //데이터 값 역순으로 list 출력
                    // for(let i=0; i<this.result.length; i++){
                    //     console.log('axaxxa:'+this.result[0].movie_num) // for문 안에서 번호 값을 확인
                    // }

                    // sort() --> 배열을 정렬하는 메소드 ,오름차순이나 내림차순 가능.( a-b(오름차순 정렬), b-a(내림차순 정렬))
                    // [...data] --> 스프레드 연산자를 사용하여  data 배열의 모든요소를 새 배열로 복사
                    // this.result =[...this.result].sort((a,b)=>b.movie_num - a.movie_num);

                    // console.log('가나다라마바사아:'+this.result.length)


                    // A로 시작하고 n으로 끝나는 것만 출력 (1)
                    // for(let i=0; i<this.result.length; i++){
                    //     console.log('장르:'+this.result[i].genre)
                    //     if(this.result[i].genre.startsWith('A') && this.result[i].genre.endsWith('n')){
                    //         console.log('선택장르:'+this.result[i].genre)
                    //         this.result= response.data.result
                    //     }
                    // }

                    // A로 시작하고 n으로 끝나는 것만 출력 (2) 
                    // this.result = this.result.filter(item=>
                    //     item.genre.startsWith('A') && item.genre.endsWith('n')
                    // );

                    // console.log('A로시작하고n으로 끝나는 장르:',this.result)

                    // 배급사가 Warner Bros.인 리스트 출력 + 장르가 판타지인
                    // this.result = this.result.filter(item=>
                    //     item.producer.startsWith('Warner') && item.genre === 'Fantasy'
                    //  )

                    // const arr = {
                    //     movie_num: 11
                    //     , genre: '스프레드'
                    //     , title: '스프레드'
                    //     , directore: '스프레드'
                    //     , producer: '(주)스프레드'
                    //     , release_date: '2024-11-09'
                    //     , runtime: '1242'
                    //     , description: '스프레드'
                    //     , bcode: 7777
                    //     , rating: 8.0
                    // }

                    // this.result = [...this.result, arr]
                    // console.log('스프레드:' + arr)// 구조: [obj +obj]


                    // const users = [
                    //     { name: 'Alice', age: 25 },
                    //     { name: 'Bob', age: 30 },
                    //     { name: 'Charlie', age: 35 }
                    // ];

                    // const users2 = [
                    //     { name: 'xxx', age: 21 },
                    //     { name: 'bb', age: 23 },
                    //     { name: 'ccc', age: 31 }
                    // ];

                    // const names = users.map(user => user.name);

                    // const newUsers = [...users, ...users2]

                    // console.log('name:'+names);
                    // const newUsersName = newUsers.map(user=>user.name);
                    // console.log('newUsers',newUsersName);

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

        // 평점이 없을시 출력문구
        rate(row) {
            if (row.rating) {
                return row.rating;
            } else {
                return '평점x';
            }
        },

        // tt(row){
        //     // for(let i=0; i<this.result.length; i++){
        //     //             if(this.result[i].movie_num%2 == 0){
        //     //                 this.result[i].title = 'title1';
        //     //             } else {
        //     //                 this.result[i].title = 'title2';
        //     //             }
        //     //         }

        //     if(row.movie_num % 2 === 0){
        //         console.log('title1');
        //         return 'title1';
        //     } else {
        //         return 'title2';
        //     }
        // }

    }
}

</script>

<style>
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