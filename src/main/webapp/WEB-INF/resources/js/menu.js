function getDishes(key, value) {
    let url = window.location.href;
    if (!url.includes('?')) {
        window.location.href = url + '?' + key + "=" + value;
    } else {
        let arrayQuestion = url.split('?');
        if (arrayQuestion[1] === "") {
            window.location.href = url + key + "=" + value;
        } else {
            let arrayAmpersand = arrayQuestion[1].split('&');
            let hasKey = false;
            for (let i = 0; i < arrayAmpersand.length; i++) {
                if (arrayAmpersand[i].includes(key + '=')) {
                    arrayAmpersand[i] = key + '=' + value;
                    hasKey = true;
                }
            }
            if (hasKey) {
                window.location.href = arrayQuestion[0] + '?' + arrayAmpersand.join('&');
            } else {
                window.location.href = url + '&' + key + '=' + value;
            }
        }
    }
}

function getSort(field, order) {
    let ur = window.location.href;
    if (ur.includes('sort=' + field + ',' + 'desc')) {
        getDishes('sort', field + ',' + order);
    } else if (ur.includes('sort=' + field + ',' + 'asc')) {
        if (ur.includes('?' + 'sort=' + field + ',' + 'asc')) {
            window.location.href = ur.replace('?sort=' + field + ',' + 'asc', "?");
        } else if (ur.includes('sort=' + field + ',' + 'asc' + '&')) {
            window.location.href = ur.replace('sort=' + field + ',' + 'asc&', "");
        } else {
            window.location.href = ur.replace('&sort=' + field + ',' + 'asc', "");
        }
    } else {
        getDishes('sort', field + ',' + order);
    }
}

function getSortName(field, order) {
    let ur = window.location.href;
    if (ur.includes('sort=' + field + ',' + 'asc')) {
        getDishes('sort', field + ',' + order);
    } else if (ur.includes('sort=' + field + ',' + 'desc')) {
        if (ur.includes('?' + 'sort=' + field + ',' + 'desc')) {
            window.location.href = ur.replace('?sort=' + field + ',' + 'desc', "?");
        } else if (ur.includes('sort=' + field + ',' + 'desc' + '&')) {
            window.location.href = ur.replace('sort=' + field + ',' + 'desc&', "");
        } else {
            window.location.href = ur.replace('&sort=' + field + ',' + 'desc', "");
        }
    } else {
        getDishes('sort', field + ',' + order);
    }
}

function checkbox(key) {
    let chbox = document.getElementById(key);
    let meth = chbox.checked;
    getDishes(key, meth);
}

function put(id) {
    $.ajax({
        type: 'GET',
        url:'./../../menu/put/' + id,
        datatype: 'json',
        success: function (data) {
            showCart(data);
        }
    })
}

function remove(id) {
    $.ajax({
        type: 'GET',
        url:'./../../menu/remove/' + id,
        datatype: 'json',
        success: function (data) {
            showCart(data);
        }
    })
}

function showCart(data) {
    let element = document.getElementById("cart");
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
    let list = data['products'];
    let reserved = data['reserved'];
    let htmlSnippet;
    let htmlSum;
    let htmlReserve;
    let arrayProducts = [];
    let commonSum = 0;
    for (i = 0; i < list.length; i++) {
        let param = list[i];
        let count = Number(param['count']);
        let price = Number(param['dish']['price']);
        let sum = count * price;
        commonSum = commonSum + sum;
        htmlSnippet =`
                <div id="cart" >
                    <div class="row">
                        <span class="pl-2">${param['dish']['name']}</span>
                    </div>
                    <div class="row">
                        <div class="col-4">
                        </div>
                        <div class="col-8">
                            <span>${param['count']} x ${param['dish']['price']} = ${sum} руб. </span>
                        </div>
                    </div>
                </div>
            `;
        arrayProducts.push(htmlSnippet);
    }
    if (commonSum !== 0) {
        htmlSum =`
        <div class="row">
            <div class="pl-2 p-4">
                <span>Общая сумма...............${commonSum} руб. </span>
            </div>
        </div>
        <form action="./../../menu/process-order" method="get">
            <button type="submit" class="btn btn-secondary width100">Перейти к оформлению</button>
        </form>
    `;
    } else {
        htmlSum =`
        <div class="row">
            <span>Товаров не выбрано.</span>
        </div>

    `;
    }
    arrayProducts.push(htmlSum);
    if (reserved && commonSum > 0) {
        htmlReserve =`
        <form action="./../../reservation/pre-order" method="get">
            <button type="submit" class="btn btn-secondary width100">Привязка к бронированию</button>
        </form>
    `;
    }
    arrayProducts.push(htmlReserve);
    element.innerHTML = arrayProducts.join('');
}

function putForShowRow(id) {
    $.ajax({
        type: 'GET',
        url:'./../../menu/put/' + id,
        datatype: 'json',
        success: function (data) {
            showRow();
        }
    })
}

function showRow() {
    let element = document.getElementById("showAdd");
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
    let htmlSnippet;
    htmlSnippet =`
            <div  class="alert alert-dismissible alert-light">
                <button type="button" class="close" data-dismiss="alert">x</button>
                <strong>Блюдо добавлено!</strong>
            </div>
        `;
    element.innerHTML = htmlSnippet;
}

function ratingDish(id, number) {
    $.ajax({
        type: 'POST',
        url:'./../../menu/rating-dish/' + id + '/' + number,
        datatype: 'json',
        success: function (data) {

        }
    })
}


function showOrder(data) {
    let element = document.getElementById("cart");
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
    let list = data['cart']['products'];
    let dates = data;
    let htmlStartForm;
    let htmlSnippet;
    let htmlSum;
    let htmlAboutUser;
    let htmlFinishForm;
    let arrayProducts = [];
    let commonSum = 0;
    for (i = 0; i < list.length; i++) {
        let param = list[i];
        let count = Number(param['count']);
        let price = Number(param['dish']['price']);
        let sum = count * price;
        commonSum = commonSum + sum;
        htmlStartForm = `
                <form action="./../menu/success-order" method="post">
        `;
        arrayProducts.push(htmlStartForm);
        htmlSnippet =`
                <div id="cart" >
                    <div class="row">

                    </div>
                    <div class="row">
                        <div class="col-3">
                            <span class="pl-2">${param['dish']['name']}</span>
                        </div>
                        <div class="col-5">
                            <span>${param['count']} x ${param['dish']['price']} = ${sum} руб. </span>
                        </div>
                        <div class="col-4">
                            <input type="text" id="${i}" name="${i}" value="">
                        </div>
                    </div>
                </div>
            `;
        arrayProducts.push(htmlSnippet);
    }
    if (commonSum !== 0) {
        htmlSum =`
        <div class="row">
            <div class="pl-2 p-4">
                <span>Общая сумма...............${commonSum} руб. </span>
            </div>
        </div>
    `;
    } else {
        htmlSum =`
        <div class="row">
            <span>Товаров не выбрано.</span>
        </div>

    `;
    }
    arrayProducts.push(htmlSum);
    htmlAboutUser =`
        <div class="row">
            <span>Имя:</span>
            <input type="text" id="name" name="name" value="${dates['name']}" >
        </div>
        <div class="row">
            <span>Телефон:</span>
            <input type="text" id="phone" name="phone" value="${dates['phone']}" >
        </div>
        <div class="row">
            <span>Адрес:</span>
            <input type="text" id="address" name="address" value="${dates['address']}" >
        </div>
        <div class="row">
            <span>Комментарий:</span>
            <input type="text" id="comment" name="comment" value="">
        </div>
    `;
    arrayProducts.push(htmlAboutUser);
    htmlFinishForm = `
            <button type="submit" class="btn btn-secondary width100">Оформить</button>
        </form>
        `;
    arrayProducts.push(htmlFinishForm);
    element.innerHTML = arrayProducts.join('');
}
