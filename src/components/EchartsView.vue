<template>
    <div :id="idName" style="width: 100%; height:100%"></div>
</template>
<script setup lang="ts">
import * as echarts from 'echarts/core';
import {
  TitleComponent,
  TooltipComponent,
  GridComponent,
  VisualMapComponent,
} from 'echarts/components';
import type{
    TitleComponentOption,
    TooltipComponentOption,
    GridComponentOption,
    VisualMapComponentOption
} from 'echarts/components';
import { LineChart } from 'echarts/charts';
import type{ LineSeriesOption } from 'echarts/charts';
import { UniversalTransition } from 'echarts/features';
import { CanvasRenderer } from 'echarts/renderers';
import { onMounted } from 'vue';
import { defineProps } from 'vue';
const prop = defineProps({
    idName: {
        type: String,
        default: 'main'
    },
    dataList: {
        type: Array<Info>,
        default: []
    },
    typeData: {
      type: String,
      default: ''
    }
})

echarts.use([
  TitleComponent,
  TooltipComponent,
  GridComponent,
  VisualMapComponent,
  LineChart,
  CanvasRenderer,
  UniversalTransition
]);

type EChartsOption = echarts.ComposeOption<
  | TitleComponentOption
  | TooltipComponentOption
  | GridComponentOption
  | VisualMapComponentOption
  | LineSeriesOption
>;

type Info = {
  [index: string]: unknown
}

// var chartDom = document.getElementById('main')!;
// var myChart = echarts.init(chartDom);
var option: EChartsOption;

// prettier-ignore
const xList = prop.dataList.map((item) => {
    return item.time;
})
const yList = prop.dataList.map((item) => {
    return item.value;
})
// const maxVal = Number(Math.max(...(yList as any)))
// const minVal = Number(Math.min(...(yList as any)))
// console.log('maxVal=-=',maxVal, minVal)
// console.log('yList=-=',yList)
option = {
  // Make gradient line here
  visualMap: [
    {
      show: false,
      type: 'continuous',
      seriesIndex: 0,
      min: 0,
      max: 5
    }
  ],

  title: [
    {
      left: 'center',
      text: ''
    }
  ],
  tooltip: {
    trigger: 'axis'
  },
  xAxis: [
    {
      data: xList as any,
      name: '时间',
      // axisLine: {
      //   show: true,
      //   // symbol: ['none', 'arrow']
      // }
    }
  ],
  yAxis: [
    {
      // axisLabel: {
      //   fontSize: 10
      // },
      //interval: maxVal < 5 ? 0.25 : (maxVal - minVal)/5
      // interval: 0.25
      name: `${prop.typeData}/m`,
      // nameLocation: 'middle',
      axisLine: {
        show: true,
        // symbol: ['none', 'arrow']
      }
    } 
  ],
  grid: [
    {
    //   bottom: '60%'
      top: 40,
      right: 45,
      bottom: 30,
      left: 60
    }
  ],
  series: [
    {
      type: 'line',
      showSymbol: false,
      data: yList as any
    }
  ]
};
onMounted(() => {
    var myChart = echarts.init(document.getElementById(prop.idName)!);
    option && myChart.setOption(option);
    // window.onresize = myChart.resize as any;
    // window.onresize = function(){
    //     myChart.resize()
    // }
    window.addEventListener('resize', function(){
        myChart.resize()
    })
})
// option && myChart.setOption(option);

</script>