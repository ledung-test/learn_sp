const submitFormRegister = () => {
    const name = document.getElementById("inputName");
    const email = document.getElementById("inputEmail");
    const password = document.getElementById("inputPassword");
    const confPass = document.getElementById("inputConfPassword");
    const url = `/api/auth/register`;
    axios.post(url, {
        name: name.value,
        email: email.value,
        password: password.value,
        confPassword: confPass.value
    }).then(function (response) {
        alert('Đăng ký thành công');
        window.location.href = "/dang-nhap";
    }).catch(function (error) {
        console.log(error);
    });
}