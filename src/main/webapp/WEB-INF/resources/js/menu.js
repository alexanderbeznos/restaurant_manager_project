function getDishes(key, value) {
    let url = window.location.href;
    let nextPath;
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
                window.location.href = arrayQuestion[0] + '?' + arrayAmpersand.join('&')
            } else {
                window.location.href = url + '&' + key + '=' + value;
            }
        }
    }
}