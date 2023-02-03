function publishMissingAnimal(){

    var missingFileForm=document.getElementById("missingFileForm")
    var missingKind=document.getElementById("missingKind").value
    var missingVariety=document.getElementById("missingVariety").value
    var missingSex=document.getElementById("missingSex").value
    var missingBody=document.getElementById("missingBodyShape").value
    var missingColor=document.getElementById("missingColor").value
    var missingAge=document.getElementById("missingAge").value
    var  missingDate=document.getElementById("missingDate").value
    var missingPlace=document.getElementById("missingPlace").value
    var missingRemark=document.getElementById("missingRemark").value

    if (missingDate==""||missingFileForm.files.length==false||missingKind==""||missingVariety==""||missingSex==""||missingBodyShape==""||missingColor==""||missingAge==""||missingPlace==""||missingRemark=="") { alert('資料請確實填寫');  return}
    var fd=new FormData();
    fd.append("missingAnimalPhoto",missingFileForm.files[0])
    fd.append("missingKind",missingKind)
    fd.append("missingVariety",missingVariety)
    fd.append("missingSex",missingSex)
    fd.append("missingBody",missingBody)
    fd.append("missingColor",missingColor)
    fd.append("missingAge",missingAge)
    fd.append("missingDate",missingDate)
    fd.append("missingPlace",missingPlace)
    fd.append("missingRemark",missingRemark)

    var xhr = new XMLHttpRequest();
    xhr.addEventListener('load',function(){
        document.getElementById("missingFileForm").value=""
        document.getElementById("missingKind").value=""
        document.getElementById("missingVariety").value=""
        document.getElementById("missingSex").value=""
        document.getElementById("missingBodyShape").value=""
        document.getElementById("missingColor").value=""
        document.getElementById("missingAge").value=""
        document.getElementById("missingPlace").value=""
        document.getElementById("missingRemark").value=""
        document.getElementById("missingDate").value=""
        alert('協尋資料刊登成功')
    })
    xhr.open('post', 'http://localhost:8080/missing_animal/textData');

    xhr.send(fd);

}