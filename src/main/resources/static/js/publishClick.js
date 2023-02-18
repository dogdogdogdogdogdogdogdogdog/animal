
function publishAnimal(){

    let name=document.getElementById("createName").value
    let kind=document.getElementById("createkind").value
    let variety=document.getElementById("createVariety").value
    let sex=document.getElementById("createSex").value
    let age=document.getElementById("createAge").value
    let body=document.getElementById("createBodyShape").value
    let color=document.getElementById("createColor").value
    let sterilization=document.getElementById("createLigation").value
    let bacterin=document.getElementById("createBacterin").value
    let area=document.getElementById("createAddress").value
    let description=document.getElementById("createRemark").value
    let file=document.getElementById("fileForm").files[0]
    let fd=new FormData();
    fd.append("animalPhoto",file)
    fd.append("userId",user.id)
    fd.append("animalName",name)
    fd.append("animalKind",kind)
    fd.append("animalVariety",variety)
    fd.append("animalSex",sex)
    fd.append("animalAge",age)
    fd.append("animalBodysize",body)
    fd.append("animalColor",color)
    fd.append("animalSterilization",sterilization)
    fd.append("animalBacterin",bacterin)
    fd.append("photoUrl",user.id)
    fd.append("area",area)
    fd.append("description",description)
    let xhr=new XMLHttpRequest()
    xhr.addEventListener("load", function (){
        if(xhr.status==201){

            alert('新增成功')
        }
    })
    xhr.open('post','/personalAnimals')
   xhr.send(fd)



}
