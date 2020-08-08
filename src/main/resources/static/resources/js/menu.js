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
                        <span class="pl-4 basket-text">${param['dish']['name']}</span>
                    </div>
                    <div class="row">
                        <div class="col-4">
                        </div>
                        <div class="col-8">
                            <span class="basket-text">${param['count']} x ${param['dish']['price']} = ${sum} руб. </span>
                        </div>
                    </div>
                </div>
            `;
        arrayProducts.push(htmlSnippet);
    }
    if (commonSum !== 0) {
        htmlSum =`
        <div class="row">
            <div class="p-4">
                <span class="basket-text">Общая сумма..........${commonSum} руб. </span>
            </div>
        </div>
        <form action="./../../menu/process-order" method="get">
            <button type="submit" class="btn btn-secondary width100">Перейти к оформлению</button>
        </form>
    `;
    } else {
        htmlSum =`
        <div class="row">
            <span class="pl-4 basket-text">Товаров не выбрано.</span>
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
                <strong class="dish-added">Блюдо добавлено!</strong>
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
    let startPage;
    let middlePage;
    let finishPage;
    let arrayProducts = [];
    let commonSum = 0;
    htmlStartForm = `
                <form action="./../menu/success-order" method="post">
        `;
    arrayProducts.push(htmlStartForm);
    startPage = `
                <div class="row">
                        <div class="col-8">
                            <div class="row">
                                <div class="col-10">
                                <span class="basket-text head-text text-secondary">Ваш заказ</span>
                            </div>
                        </div>
                        <div class="spacing5"></div>
        `;
    arrayProducts.push(startPage);
    for (i = 0; i < list.length; i++) {
        let param = list[i];
        let count = Number(param['count']);
        let price = Number(param['dish']['price']);
        let sum = count * price;
        commonSum = commonSum + sum;
        htmlSnippet =`
                <div id="cart" >
                     <div class="row">
                        <div class="col-3">
                            <span class="basket-text">${param['dish']['name']}</span>
                        </div>
                        <div class="col-3">
                            <span class="basket-text">${param['count']} x ${param['dish']['price']} = ${sum} руб. </span>
                        </div>
                        <div class="col-4">
                            <input type="text" id="${i}" name="${i}" value="" class="form-control inp basket-text black-text" placeholder="Комментарий к блюду">
                        </div>
                        <div class="col-2"></div>
                    </div>
                </div>
            `;
        arrayProducts.push(htmlSnippet);
    }
    if (commonSum !== 0) {
        htmlSum =`
        <div class="spacing30"></div>
        <div class="row">
            <div class="col-6">
                <span class="basket-text ">Общая сумма..............................${commonSum} руб. </span>
            </div>
            <div class="col-4">
                <input type="text" id="comment" name="comment" value="" class="form-control inp basket-text black-text" placeholder="Комментарий к заказу">
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
    middlePage = `
                </div>
                        <div class="col-4">
        `;
    arrayProducts.push(middlePage);
    htmlAboutUser =`
        <div class="spacing5"></div>
        <div class="row">
            <span class="basket-text head-text text-secondary">Имя</span>
            <input type="text" id="name" name="name" value="${dates['name'] == null ? '' : dates['name']}" class="form-control inp basket-text black-text">
        </div>
        <div class="spacing11"></div>
        <div class="row">
            <span class="basket-text head-text text-secondary">Телефон</span>
            <input type="text" id="phone" name="phone" value="${dates['phone'] == null ? '' : dates['phone']}" class="form-control inp basket-text black-text">
        </div>
        <div class="spacing11"></div>
        <div class="row">
            <span class="basket-tex head-text text-secondary">Адрес</span>
            <input type="text" id="address" name="address" value="${dates['address'] == null ? '' : dates['address']}" class="form-control inp basket-text black-text">
        </div>
        <div class="spacing50"></div>
    `;
    arrayProducts.push(htmlAboutUser);
    finishPage = `
                </div>
                    </div>
        `;
    arrayProducts.push(finishPage);
    htmlFinishForm = `
            <div class="spacing50"></div>
            <div class="row">
                <div class="col-8">
                    <div class="col-10 pad-0">
                        <button type="submit" class="btn btn-secondary btn-success width100">Оформить</button>
                    </div>
                </div>
                <div class="col-4 pad-0">
                    <a href="./../menu/category/1" class="card-name width100">
                        <button type="button" class="btn btn-secondary width100">Вернуться к меню</button>
                    </a>
                </div>
                
            </div>
        </form>
        `;
    arrayProducts.push(htmlFinishForm);
    element.innerHTML = arrayProducts.join('');
}
