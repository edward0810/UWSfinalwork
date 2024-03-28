
const toZero: (n: number)=>string = (n) => {
  if(n < 10){
    return '0' + n;
  }
  else{
    return '' + n;
  }
}

const excel: (data: any, fileName: string)=> any = (data, fileName) => {
  return downloadF(data, fileName, 'application/vnd.ms-excel')
}
const downloadF: (data: any, fileName: string, mineType: string)=> any = (data: any, fileName='filename', mineType: string) => {
  const blob = new Blob([data],{type:mineType})
  let url = window.URL.createObjectURL(blob)
  let link = document.createElement('a')
  link.style.display = "none";
  link.href = url
  link.download = fileName
  document.body.appendChild(link)
  link.click()
  document.body.removeChild(link);
  window.URL.revokeObjectURL((link as unknown) as string)
  return true
}

export {
  toZero,
  excel
}