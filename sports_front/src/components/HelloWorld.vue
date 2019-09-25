<template>
   <el-col
    :span="20"
    :offset="2"
    style="height:100%"
  >
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
        <!-- <span class="condition-name" >冲突阈值:</span>
        <el-input v-model="conflictVal" placeholder="请输入0-1之间的数字" style="width:150px;float:left"></el-input> -->
        <el-button type="primary" icon="el-icon-search" style="float:left" @click="initPredictions">搜索</el-button>     
        <el-alert
          :title="anaFinished?'分析成功':'正在分析中'"
          :type="anaFinished?'success':'error'"
          effect="dark"
          :style="{display:getFinished?'':'none'}"
          style="width: 200px;float: left;margin-left: 20px;"
          :closable="false"
          >
        </el-alert>
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
          :row-class-name="tableRowClassName"
          >
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
            sortable
            width="100">
          </el-table-column>
          <el-table-column
            prop="matchTime"
            label="比赛时间"
            sortable
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
            prop="maxName"
            label="推荐"
            width="70">
          </el-table-column>
           <el-table-column
            prop="maxVal"
            label="概率"
            width="70">
          </el-table-column>
          <el-table-column
            prop="isWorth"
            label="值得"
            width="70">
            <template slot-scope="scope">
              <el-tag
                :type="scope.row.isWorth === true ? 'danger' : ''"
                disable-transitions>{{scope.row.isWorth === true?'是':'否'}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            prop="signStatus"
            label="标记状态"
            :filters="[{text: '已标记', value: '已标记'},{text: '', value: ''}]"
            :filter-method="filterHandler"
            width="100">
          </el-table-column>
          <el-table-column label="标记操作" width="150">
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="signMatch(scope.$index, scope.row)">标记</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="cancelSignMatch(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column> 
          <el-table-column
            prop="bifen"
            label="比分"
            width="70">
          </el-table-column>
          <el-table-column
            prop="preResult"
            label="预测结果"
            width="100">
          </el-table-column>
          <!-- <el-table-column
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
          <el-table-column
            prop="isConflict"
            label="冲突"
            width="70">
            <template slot-scope="scope">
              <el-tag
                :type="scope.row.isConflict === true ? 'danger' : ''"
                disable-transitions>{{scope.row.isConflict === true?'是':'否'}}</el-tag>
            </template>
          </el-table-column> -->
        </el-table>
      </el-row>
    </div>
</el-card>
    
      
   </el-col>
</template>

<script>
import {getPredictions,getNeedAnaNum,upload1DataApi,upload3DataApi}  from '../api/api' 

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
       loading:'',
       conflictVal:0.6,
       anaFinished:false,
       getFinished:false
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
          // 从localStorage中获取已经标记的id
          let signIdArr = localStorage.getItem('signIds')?JSON.parse(localStorage.getItem('signIds')):[]
          for(let i of list){
            let o = {}
            o['id'] = i[0]
            o['type']= i[1]
            o['changci'] = i[2]
            o['docTime'] = i[3]
            if(!i[4])o['matchTime']= ''
            else  o['matchTime'] = this.getTimeStr(new Date(i[4]))       
            o['zhudui'] = i[5]
            o['kedui'] = i[6]
            o['pankou'] = i[7]
            o['linchangpankou'] = i[8]
            let valArr = i.slice(9,14)
            o['maxVal'] = Math.max(...valArr)
            let maxIndex = valArr.indexOf( o['maxVal'])
            o['maxIndex']= maxIndex
            o['maxName'] = this.getMaxName(maxIndex)
            o['isWorth'] = this.checkWorth(maxIndex,o['maxVal'],i.slice(16,19))
            o['bifen'] = i[14]!=null?i[14]+':'+i[15]:''
            o['zbf'] = i[14]
            o['kbf'] = i[15]
            o['signStatus'] = signIdArr.indexOf(i[0])>-1?'已标记':''
            // 标记之后再出结果
            if(signIdArr.indexOf(i[0])>-1 && i[14]!=null && i[15]!=null){
              o['preResult'] = this.getPreResult(maxIndex,i[14],i[15],i[7])
            }else{
              o['preResult']= ''
            }
            //o['isConflict']=this.checkIsConflict(i[9],i[11],i[13],i[15],i[17],i[8]||i[7])
            arr.push(o)
          }
          this.tableData = arr
        })

    },
    getPreResult(maxIndex,zbf,kbf,lpk){
      if (maxIndex in [0,1,2]){           
        if((maxIndex == 0 && zbf>kbf) || (maxIndex == 1 && zbf==kbf) || (maxIndex == 2 && zbf<kbf)){
          return '红'
        }
        else{
          return '黑'
        }                 
      }
      else if (zbf-kbf-lpk==0){
        return '走'
      }
      else if(lpk==0){
        return '走'
      }      
      else if(maxIndex==3){
        let cs = lpk> 0?1:-1
        if ((zbf-kbf-lpk)*cs == 0.25){
          return '红半'
        }else if((zbf-kbf-lpk)*cs>0){
          return '红'
        }else if((zbf-kbf-lpk)*cs == -0.25){
          return '黑半'
        }else{
          return '黑'
        }
      }
      else if(maxIndex==4){
        let cs = lpk> 0?1:-1
        if ((zbf-kbf-lpk)*cs == -0.25){
          return '红半'
        }else if((zbf-kbf-lpk)*cs<0){
          return '红'
        }else if((zbf-kbf-lpk)*cs == 0.25){
          return '黑半'
        }else{
          return '黑'
        }
      }
    },
    signMatch(index,row){
      debugger
      let signIdArr = localStorage.getItem('signIds')?JSON.parse(localStorage.getItem('signIds')):[]
      let set = new Set(signIdArr)
      set.add(row.id)
      signIdArr = Array.from(set)
      localStorage.setItem('signIds',JSON.stringify(signIdArr))
      let o = row
      o['signStatus'] = '已标记'
      if(o['zbf']!=null && o['kbf']!=null){
        o['preResult'] = this.getPreResult(o['maxIndex'],o['zbf'],o['kbf'],o['pankou'])
      }else{
        o['preResult']= ''
      }
      this.tableData[index] = o

    },
    cancelSignMatch(index,row){
      let o = row
      let signIdArr = localStorage.getItem('signIds')?JSON.parse(localStorage.getItem('signIds')):[]
      let set = new Set(signIdArr)
      if(!set.has(o.id)){
        return 
      }
      set.delete(o.id)
      signIdArr = Array.from(set)
      localStorage.setItem('signIds',JSON.stringify(signIdArr))
      o['signStatus'] = ''
      o['preResult'] = ''
      this.tableData[index] = o
    },
    filterHandler(value, row, column) {
      const property = column['property'];
      return row[property] === value;
    },
    tableRowClassName({row, rowIndex}) {
      debugger
      if (row['signStatus'] === '已标记') {
        if(row['preResult'].indexOf('红')>-1){
          return 'error-row'
        }else if(row['preResult'].indexOf('黑')>-1){
          return 'success-row'
        }
        return 'warning-row';
      }
      
      return '';
    },
    getNeednum(){
      getNeedAnaNum().then(res=>{
        let data = res.data
        if (!res.data || !res.data.success){
          this.$message.error('查询失败');
        } 
        let num = data.data
        if(num>0){
          this.anaFinished = false
        }else{
          this.anaFinished = true
        }
        this.getFinished = true
      })
    },
    checkWorth(index,val,pArr){
      if(index>2){
        return true
      }else if(pArr[index]*val<1.02){
        return false
      }
      return true
    },
    getMaxName(index){
      switch(index){
        case 0:
          return '胜'
        case 1:
          return '平'
        case 2:
          return '负'
        case 3:
          return '上'
        case 4:
          return '下'
        default:
          return ''
      }
    },
    checkIsConflict(smax,pmax,fmax,shmax,xmax,pankou){
      let conflictVal = this.conflictVal
      if(smax>conflictVal && pmax>conflictVal){
        return true
      }else if(pmax>conflictVal && fmax>conflictVal){
        return true
      }else if(fmax>conflictVal && smax>conflictVal){
        return true
      }else if(shmax>conflictVal && xmax>conflictVal){
        return true
      }else if(pankou>0 && smax>conflictVal && xmax>conflictVal){
        return true
      }else if(pankou>0 && (pmax>conflictVal || fmax>conflictVal) && shmax>conflictVal ){
        return true
      }else if(pankou<0 && fmax>conflictVal && xmax>conflictVal){
        return true
      }else if(pankou<0 && (smax>conflictVal || pmax>conflictVal) && shmax>conflictVal){
        return true
      }
      return false

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
    this.getNeednum()
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
