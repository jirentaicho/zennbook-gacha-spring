<template>
    <h1>松本匡生</h1>
</template>


<script>
import axios from 'axios'
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  mounted(){
      axios.post('http://localhost:8080/sample',{"id":5})
        .then(res => {
            console.log(res)
        })
  }
}
</script>
