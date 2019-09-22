<template>
  <div class="hello">
    <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span class="condition-name" >比赛时间:</span>
       <el-date-picker
          v-model="times"
          type="datetimerange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          style="float:left"
          >
        </el-date-picker> 
        <span class="condition-name" >概率阈值:</span>
        <el-input v-model="num" placeholder="请输入0-1之间的数字" style="width:150px;float:left"></el-input>
        <el-button type="primary" icon="el-icon-search" style="float:left" @click="initPredictions">搜索</el-button>
        
          <el-upload
          class="upload-demo"
          :action="upload1Datapath" 
          :on-success="uploadSuccess"
          :before-upload="startUpload"      
          :show-file-list="false"                 
          :file-list="fileList"
          style="display: inline-flex;"
          >
          <el-button size="small" type="primary">1版上传</el-button>  
        </el-upload>
        <el-upload
          class="upload-demo"
          :action="upload3Datapath"
          :on-success="uploadSuccess"
          :before-upload="startUpload"         
          :show-file-list="false"                 
          :file-list="fileList"
          style="display: inline-flex;">
          <el-button size="small" type="primary">3版上传</el-button>  
        </el-upload>
        
        
    </div>
    <div>
      <el-row>
         <el-table
          :data="tableData"
          style="width: 100%"
           height="750"
          :cell-class-name="setCellClass">
          <el-table-column
            prop="type"
            label="类型"
            width="80">
          </el-table-column>
          <el-table-column
            prop="changci"
            label="场次"
            width="80">
          </el-table-column>
          <el-table-column
            prop="docTime"
            label="采集时间"
            width="100">
          </el-table-column>
          <el-table-column
            prop="matchTime"
            label="比赛时间"
            width="140">
          </el-table-column>
          <el-table-column
            prop="zhudui"
            label="主队"
            width="100">
          </el-table-column>
          <el-table-column
            prop="kedui"
            label="客队"
            width="100">
          </el-table-column>
          <el-table-column
            prop="pankou"
            label="盘口"
            width="60">
          </el-table-column>
          <el-table-column
            prop="linchangpankou"
            label="临场"
            width="60">
          </el-table-column>
          <el-table-column
            prop="smax"
            label="胜最大"
            width="70">
          </el-table-column>
          <el-table-column
            prop="smin"
            label="胜最小"
            width="70">
          </el-table-column>
          <el-table-column
            prop="pmax"
            label="平最大"
            width="70">
          </el-table-column>
          <el-table-column
            prop="pmin"
            label="平最小"
            width="70">
          </el-table-column>
          <el-table-column
            prop="fmax"
            label="负最大"
            width="70">
          </el-table-column>
          <el-table-column
            prop="fmin"
            label="负最小"
            width="70">
          </el-table-column>
          <el-table-column
            prop="shmax"
            label="上最大"
            width="70">
          </el-table-column>
          <el-table-column
            prop="shmax"
            label="上最小"
            width="70">
          </el-table-column>
          <el-table-column
            prop="xmax"
            label="下最大"
            width="70">
          </el-table-column>
          <el-table-column
            prop="xmin"
            label="下最小"
            width="70"
            >
          </el-table-column>
        </el-table>
      </el-row>
    </div>
</el-card>
    
      
  </div>
</template>

<script>
import {getPredictions,upload1DataApi,upload3DataApi}  from '../api/api' 

export default {
  name: 'HelloWorld',
  data(){
    return{
       times: [new Date(2000, 10, 10, 10, 10), new Date(2000, 10, 11, 10, 10)],
       num:0.75,
       tableData: [],
       fileList:[],
       upload1Datapath:'',
       upload3Datapath:'',
       loading:''
    }
  },
  methods:{
    initPredictions(){        
        let startDate = new Date(new Date(this.times[0]).toLocaleDateString()).getTime()
        let endDate= new Date(new Date(this.times[1]).toLocaleDateString()).getTime()
        getPredictions({
          startDate:startDate,
          endDate:endDate,
          startTime:new Date(this.times[0]).getTime(),
          endTime:new Date(this.times[1]).getTime(),
          value:this.num
        }).then(res=>{
          let data = res.data
          if (!res.data || !res.data.success){
            this.$message.error('查询失败');
          }          
          let list = data.data
          let arr = []
          for(let i of list){
            let o = {}
            o['type']= i[1]
            o['changci'] = i[2]
            o['docTime'] = i[3]
            if(!i[4])o['matchTime']= ''
            else  o['matchTime'] = this.getTimeStr(new Date(i[4]))       
            o['zhudui'] = i[5]
            o['kedui'] = i[6]
            o['pankou'] = i[7]
            o['linchangpankou'] = i[8]
            o['smax'] = i[9]
            o['smin'] = i[10]
            o['pmax'] = i[11]
            o['pmin'] = i[12]
            o['fmax'] = i[13]
            o['fmin'] = i[14]
            o['shmax'] = i[15]
            o['shmin'] = i[16]
            o['xmax'] = i[17]
            o['xmin'] = i[18]
            arr.push(o)
          }
          this.tableData = arr
        })

    },
    setCellClass({row, column, rowIndex, columnIndex}){
      if(columnIndex==8){       
        return this.getClassName(row.smax)
      }else if(columnIndex==10){
        return this.getClassName(row.pmax)
      }else if(columnIndex==12){
        return this.getClassName(row.fmax)
      }else if(columnIndex==14){
        return this.getClassName(row.shmax)
      }else if(columnIndex==16){
        return this.getClassName(row.xmax)
      }
      
    },
    getClassName(val){
      if(val>= this.num+0.2){
        return 'third-level'
      }else if(val>= this.num+0.1){
        return 'second-level'
      }else if(val>=this.num){
        return 'first-level'
      }
      return ''
    },
    getTimeStr(d){     
      var yy = d.getFullYear();      //年
      var mm = d.getMonth() + 1;     //月
      var dd = d.getDate();          //日
      var hh = d.getHours();         //时
      var ii = d.getMinutes();       //分
      // var ss = d.getSeconds();       //秒
      var clock = yy + "-";
      if(mm < 10) clock += "0";
      clock += mm + "-";
      if(dd < 10) clock += "0";
      clock += dd + " ";
      if(hh < 10) clock += "0";
      clock += hh + ":";
      if (ii < 10) clock += '0'; 
      clock += ii;
      // if (ss < 10) clock += '0'; 
      // clock += ss;
      return clock
    },
    uploadSuccess(){
      if(this.loading){
        this.loading.close()
      }
      this.$message.success('上传成功');
      
    },
    startUpload(){
      this.loading = this.$loading({
        lock: true,
        text: '正在上传...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      });
      return true
    }
  },
  mounted(){   
    let startTime = new Date() - 3*60*60*1000//3小时前
    let endTime = startTime + 24*60*60*1000
    this.times = [startTime,endTime]
    this.initPredictions()
    this.upload1Datapath = upload1DataApi
    this.upload3Datapath = upload3DataApi
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.condition-name{
  float: left;
  margin-top: 10px
}



</style>
