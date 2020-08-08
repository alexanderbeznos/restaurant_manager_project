function takeItem(number) {
    let url = window.location.href;
    let array = url.split('?')

    $.ajax({
        type: 'GET',
        url:'./../kitchen/take-dish/' + number + '?' + array[1],
        datatype: 'json',
        success: function (data) {
            afterTakeItem();
        }
    })
}

function afterTakeItem() {
    window.location.href = './all-orders';
}

function getPageItems(key, value) {
    let url = window.location.href;
    if (!url.includes('?')) {
        window.location.href = url + '?' + key + "=" + value;
    } else {
        let array = url.split('=');
        window.location.href = array[0] + '=' + value;
    }

}