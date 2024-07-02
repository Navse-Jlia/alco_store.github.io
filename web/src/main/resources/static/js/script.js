// fetch('http://localhost:8080/products')
//     .then(response => {
//         if (!response.ok) {
//             throw new Error('Network response was not ok');
//         }
//         return response.json(); // Парсим ответ в формате JSON
//     })
//     .then(data => {
//         console.log(data);
//     })
//     .catch(error => {
//         console.error('There was a problem with the fetch operation:', error);
//     });

// function runScript() {
//     alert("Товар добавлен в корзину!")
// }
//
// function notYet() {
//     alert("Еще не готово")
// }

function deleteRequest(id) {
    console.log("123")
    $.ajax({
        url: 'api/products/' + id,
        method: 'DELETE'
    });
}

function saveProduct() {
    console.log(345);

    $.ajax({
        type: "POST",
        url: "/api/products/",
        contentType: "application/json",
    });
}

// async function uploadFile() {
//     let formData = new FormData();
//     formData.append("data", fileupload.files[0]);
//     await fetch('/api/catalog.html/', {
//         method: "POST",
//         body: formData
//     }).then(result => result.text())
//         .then(text => alert(text));
// }

// При успешной загрузке изображения
// Функция для выполнения AJAX-запроса на загрузку изображения
function uploadImage() {
    var formData = new FormData();
    formData.append('image', $('#imageInput')[0].files[0]);

    $.ajax({
        url: '/upload',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function (response) {
            $("#imagePath").val(formData.getOriginalFileName);
            console.log('Image uploaded successfully');
            alert("Загружено!");
            // Здесь вы можете добавить логику обработки ответа
        },
        error: function (xhr, status, error) {
            console.error('Error uploading image:', error);
        }
    });
}


