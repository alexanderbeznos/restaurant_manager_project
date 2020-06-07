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




// function getSort(field, order) {
//     let ur = window.location.href;
//     if (ur.includes('sort=' + field + ',' + 'desc')) {
//         getDishes('sort', field + ',' + order);
//     } else if (ur.includes('sort=' + field + ',' + 'asc')) {
//         if (ur.includes('&')) {
//             window.location.href = ur.replace('&sort=' + field + ',' + 'asc', "");
//         } else {
//             window.location.href = ur.replace('sort=' + field + ',' + 'asc', "");
//         }
//     } else {
//         getDishes('sort', field + ',' + order);
//     }
// }
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
// function getSortName(field, order) {
//     let ur = window.location.href;
//     if (ur.includes('sort=' + field + ',' + 'asc')) {
//         getDishes('sort', field + ',' + order);
//     } else if (ur.includes('sort=' + field + ',' + 'desc')) {
//         if (ur.includes('&')) {
//             window.location.href = ur.replace('&sort=' + field + ',' + 'desc', "");
//         } else {
//             window.location.href = ur.replace('sort=' + field + ',' + 'desc', "");
//         }
//     } else {
//         getDishes('sort', field + ',' + order);
//     }
// }
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

    // chbox.onclick = function(e) {
    //     e.preventDefault();
    //     setTimeout(() => {
    //         this.checked = !this.checked
    //     }, 0)
    // }

    // let chbox = document.querySelector('#' + key);
    // let meth = chbox.checked;
    // if (!chbox.checked) {
    //     chbox.checked = true;
    // }
    // if (chbox.checked) {
    //     chbox.checked = false;
    // }

    getDishes(key, meth);

    // if (chbox.checked) {
    //     alert('Выбран');
    // }
    // else {
    //     alert ('Не выбран');
    // }
}



