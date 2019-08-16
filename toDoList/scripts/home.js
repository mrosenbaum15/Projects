
let pastElemArr;

// for clicking on trash icon
function removeLiItem(li, liText) {
    console.log(liText);

    li.remove();

    var index = currentListElems.indexOf(liText);

    console.log(index);

    if (index > -1) {
        currentListElems.splice(index, 1);
    }

    console.log(currentListElems);


    if(ul.children.length == 0) {
        ul.classList.remove('collection')
    }

}

function openAddWindow() {
    ipcRenderer.send('scheduleItem:open');
}

function updateDate() {

    var date = new Date();
    var months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    var days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];


    var dateString = days[date.getDay()] + ", " + months[date.getMonth()] + date.getDate();
    document.getElementById("dateSection").innerHTML = dateString;
}

function addPastElems() {

    pastElemArr = localStorage.getItem('listElems');

    if(!pastElemArr) {
        return;
    }

    pastElemArr = JSON.parse(pastElemArr);

    for(var i = 0; i < pastElemArr.length; ++i) {

        ul.classList.add("collection");
        var li = document.createElement('li');
        li.classList.add("collection-item");
        var liText = pastElemArr[i];
        var liContent = document.createTextNode(liText);
        var trashIcon = document.createElement('i');
        trashIcon.classList.add('material-icons', 'trash-icon');
        trashIcon.innerHTML = 'delete';

        setTrashOnclick(trashIcon, li, liText);

        li.appendChild(liContent);
        li.appendChild(trashIcon);
        ul.appendChild(li);
        currentListElems.push(liText);

    }

}

function setTrashOnclick(trashIcon, li, liText) {

    trashIcon.onclick = function() {
        removeLiItem(li, liText);
    }
}
