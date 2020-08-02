function createRequestTables() {
    let startDateString = document.getElementById("startDate").value;
    let finishDateString = document.getElementById("finishDate").value;
    if (startDateString == "") {
        alert('Выберите время от');
    }
    if (finishDateString == "") {
        alert('Выберите время до');
    }
    let nowDate = new Date();
    let arrS = startDateString.split('.');
    let s = arrS[0];
    arrS[0] = arrS[1];
    arrS[1] = s;
    let strS = arrS.join('.');
    let arrF = finishDateString.split('.');
    let f = arrF[0];
    arrF[0] = arrF[1];
    arrF[1] = f;
    let strF = arrF.join('.');

    let sD = new Date(strS);
    let fD = new Date(strF);

    if (sD.getTime() < nowDate.getTime()) {
        alert('Выберите дату от в будущем');
        return null;
    }
    if (fD.getTime() < nowDate.getTime()) {
        alert('Выберите дату до в будущем');
        return null;
    }
    if (sD.getTime() > fD.getTime()) {
        alert('Выберите дату от раньше даты до');
        return null;
    }
    if ((fD.getTime() - sD.getTime()) < 900000 ) {
        alert('Минимальное время бронирования столика 15 минут');
        return null;
    }
    let seats = document.getElementById("seats").value;
    return {
        startDate: startDateString,
        finishDate: finishDateString,
        seats: seats
    };
}

function requestShowTables() {
    let tablesObj = createRequestTables();

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url:'./restaurant/show-tables',
        data: JSON.stringify(tablesObj),
        datatype: 'json',
        success: function (data) {
            showTables(data);
        }
    })
}

function showTables(data) {
    let element = document.getElementById("showTables");
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
    let list = data;
    let htmlStartShowTables;
    let htmlMiddleShowTables;
    let htmlFinishShowTables;
    let arrayProducts = [];
    htmlStartShowTables = `
                <div class="area plan">
        `;
    arrayProducts.push(htmlStartShowTables);
    for (let i = 0; i < list.length; i++) {
        let per = list[i];
        htmlMiddleShowTables =`
                        <div id="table${per['numberTable']}" class="${per['ready'] ? 'green' : 'red'}" onclick="showInfoAboutTable(id)">
                        </div>
        `;
        arrayProducts.push(htmlMiddleShowTables);
    }
    htmlFinishShowTables = `
                </div>
        `;
    arrayProducts.push(htmlFinishShowTables);
    element.innerHTML = arrayProducts.join('');
}

function showInfoAboutTable(id) {
    $.ajax({
        type: 'GET',
        url:'./restaurant/about-table/' + id,
        datatype: 'json',
        success: function (data) {
            changeNumberTable(data);
            countOfGuest(data);
            changeComment();
        }
    })
}

function changeNumberTable(data) {
    let element = document.getElementById("numberOfTable");
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
    let obj = data;
    let arrayProducts = [];
    let htmlStart;
    htmlStart = `
                    <div class="col-8 table-desc text-secondary height-30">
                        <span>
                            Столик
                        </span>
                    </div>
                    <div class="col-4">
                        <!--<span id="numberTable">${obj['numberOfTable']}</span>-->
                        <input id="numberTable" type="text" class="form-control inp" value="${obj['numberOfTable']}" disabled>
                    </div>

        `;
    arrayProducts.push(htmlStart);
    element.innerHTML = arrayProducts.join('');
}

function countOfGuest(data) {
    let element = document.getElementById("countOfGuest");
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
    let seat = data['quantityPeople'];
    let arrayProducts = [];
    let htmlStart;
    let htmlMiddle;
    let htmlFinish;
    htmlStart = `
                <div class="col-8 height-30">
                    <span class="table-desc text-secondary">Кол-во гостей:</span>
                </div>
                <div class="col-4">
                    <select id="amountPeople" class="form-control inp">                           
        `;
    arrayProducts.push(htmlStart);
    for (let i = 1; i <= seat; i++) {
        htmlMiddle = `
                    <option value="${i}">${i}</option>                
        `;
        arrayProducts.push(htmlMiddle);
    }
    htmlFinish = `
                    </select>
                </div>                
        `;
    arrayProducts.push(htmlFinish);
    element.innerHTML = arrayProducts.join('');
}

function changeComment() {
    let element = document.getElementById("changeComment");
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
    let arrayProducts = [];
    let htmlStart;
    htmlStart = `
            <input type="text" id="commentReserve" class="form-control width100 inp black-text" placeholder="Добавьте комментарий">
        `;
    arrayProducts.push(htmlStart);
    element.innerHTML = arrayProducts.join('');
}


function aboutTables() {
    let startDateTable = document.getElementById("startDate").value;
    let finishDateTable = document.getElementById("finishDate").value;
    if (startDateTable == "") {
        alert('Выберите время от');
    }
    if (finishDateTable == "") {
        alert('Выберите время до');
    }
    let nowDate = new Date();
    let arrS = startDateTable.split('.');
    let s = arrS[0];
    arrS[0] = arrS[1];
    arrS[1] = s;
    let strS = arrS.join('.');
    let arrF = finishDateTable.split('.');
    let f = arrF[0];
    arrF[0] = arrF[1];
    arrF[1] = f;
    let strF = arrF.join('.');

    let sD = new Date(strS);
    let fD = new Date(strF);

    if (sD.getTime() < nowDate.getTime()) {
        alert('Выберите дату от в будущем');
        return null;
    }
    if (fD.getTime() < nowDate.getTime()) {
        alert('Выберите дату до в будущем');
        return null;
    }
    if (sD.getTime() > fD.getTime()) {
        alert('Выберите дату от раньше даты до');
        return null;
    }
    if ((fD.getTime() - sD.getTime()) < 900000 ) {
        alert('Минимальное время бронирования столика 15 минут');
        return null;
    }
    let seats = document.getElementById("amountPeople").value;
    let numberTable = document.getElementById("numberTable").value;
    let comment = document.getElementById("commentReserve").value;
    return {
        startDate: startDateTable,
        finishDate: finishDateTable,
        seats: seats,
        numberTable: numberTable,
        comment: comment
    };
}

function chooseTable() {
    let tablesObj = aboutTables();

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url:'./restaurant/after-choose-table',
        data: JSON.stringify(tablesObj),
        datatype: 'json',
        success: function (data) {
            afterChooseTable(data);
        }
    })
}

function afterChooseTable(data) {
    if (!data['reservation']) {
        let element = document.getElementById("notFindTime");
        while (element.firstChild) {
            element.removeChild(element.firstChild);
        }
        let htmlTable;
        let arrayProducts = [];
        htmlTable = `
                    <span class="text-danger text-desc p-3">Столик занят в данное время</span>                                     
        `;
        arrayProducts.push(htmlTable);
        element.innerHTML = arrayProducts.join('');
    } else {
        let url = window.location.href;
        window.location.href = url + "/success-reservation/" + data['reservationId']
    }
}

function orderingCheckTime(reserveTablesId) {
    let orderingObj = aboutOrdering(reserveTablesId);

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url:'./../../reservation/order-success',
        data: JSON.stringify(orderingObj),
        datatype: 'json',
        success: function (data) {
            afterOrdering(data);
        }
    })
}

function aboutOrdering(reserveTablesId) {
    let array = document.getElementsByTagName("tr");
    let list = [];
    for(let j = 1; j < array.length; j++) {
        let row = array.item(j).children;
        let dishId = row[6].outerText;
        let timeDish = row[4].children[0].value;
        let comment = row[5].children[0].value;

        let name = row[0].outerText;
        let price = row[1].outerText;
        let count = row[2].outerText;
        let sum = row[3].outerText;

        let dish = {
            dishId: dishId,
            timeDish: timeDish,
            comment: comment,
            reserveTablesId: reserveTablesId
        };
        list.push(dish);
    }
    return list;
}

function afterOrdering(data) {
    if (!data.success) {
        alert('Выберите время подачи блюда в забронированное время');
    } else {
        window.location.href = './../../menu/category/1';
    }
}