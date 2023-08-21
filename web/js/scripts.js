// $('.message a').click(function(){
//     $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
//  });

function addToCart(id) {
    let quantity = document.getElementById('quantity').value;
    window.location.href = `addToCart?quantity=${quantity}&id=${id}`;
}