//rating star
const stars = document.querySelectorAll(".star");
const ratingValue = document.getElementById("rating-value");

let currentRating = 0;
let currentId;
let currentMovieId;
stars.forEach((star) => {
    star.addEventListener("mouseover", handleMouseOver);
    star.addEventListener("mouseout", handleMouseOut);
    star.addEventListener("click", handleStarClick);
});

function handleMouseOver() {
    const rating = parseInt(this.getAttribute("data-rating"));
    resetStars();
    highlightStars(rating);
}

function handleMouseOut() {
    resetStars();
    highlightStars(currentRating);
}

function handleStarClick() {
    currentRating = parseInt(this.getAttribute("data-rating"));
    resetStars();
    highlightStars(currentRating);
}

function resetStars() {
    stars.forEach((star) => {
        star.classList.remove("active");
    });
}

function highlightStars(rating) {
    stars.forEach((star) => {
        const starRating = parseInt(star.getAttribute("data-rating"));
        if (starRating <= rating) {
            star.classList.add("active");
        }
    });
}

//Create
const submitReview = (movieId) => {
    const reviewContent = document.getElementById("review-content");
    if (currentRating === 0) {
        alert("Mời bạn đánh giá số sao")
    } else {
        const url = `/api/reviews`;
        axios.post(url, {
            rating: currentRating,
            content: reviewContent.value,
            movieId: movieId
        }).then(function (response) {
            alert('Đánh giá thành công');
            window.location.reload();
        }).catch(function (error) {
            console.log(error);
        });
    }
}
//Get
const getReview = (reviewId) => {
    const updateContent = document.getElementById("update-review-content");
    const url = `/api/reviews/${reviewId}`;
    axios.get(url)
        .then(function (response) {
            let reviewData = response.data;
            currentRating = reviewData.rating;
            handleMouseOut();
            updateContent.value = reviewData.content;
            currentId = reviewId;
            currentMovieId = reviewData.movieId;
        })
        .then(function (updateResponse) {

        })
        .catch(function (error) {
            console.error(error);
        });
}
//Update
const updateReview = () => {
    const updateContent = document.getElementById("update-review-content");
    const url = `/api/reviews/${currentId}`;
    axios.put(url, {
        rating: currentRating,
        content: updateContent.value,
        movieId: currentMovieId
    }).then(function (response) {
        alert('Cập nhật đánh giá thành công');
        window.location.reload();
    }).catch(function (error) {
        console.log(error);
    });
}
//Delete
const deleteReview = (reviewId) => {
    console.log(reviewId);
    const isConfirmed = window.confirm('Bạn có chắc chắn muốn xóa review này?');
    if (!isConfirmed) return;
    const url = `/api/reviews/${reviewId}`;
    axios.delete(url)
        .then(function (response) {
            alert('Xóa review thành công');
            window.location.reload();
        })
        .catch(function (error) {
            console.log(error);
        });
}