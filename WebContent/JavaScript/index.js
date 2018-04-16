var lb_num = 4;
var index = 1;
var images = document.getElementsByClassName('image');
var circles = document.getElementsByClassName('circle');

window.onload = function (){
	lb();
	onMose();
}
function lb(){
	setInterval(
		function(){
		 	show(index % lb_num);
			index++;
		},
		3000
		);
}

function show(num){
	images[num].style.opacity = "1";
	for(var i = 1;i < images.length;++i){
		if(i > num){
		 			images[i].style.opacity = "0";
		 			}
		 	}
		 	
		 	index++;
}


function onMose(){
	circles[1].addEventListener("mouseover",show(1),false);
	circles[2].addEventListener("mouseover",show(2),false);
	circles[3].addEventListener("mouseover",show(3),false);

}
