$(document).ready(function() {
	// Make AJAX call to your API endpoint when the page loads
	$.ajax({
		url: '/hotelDataList',
		type: 'GET',
		success: function(data) {
			// Handle successful response here
			console.log('Data received:', data);
			var hotelDetails = JSON.parse(data);
			// You can process the data or update the UI as needed
		},
		error: function(xhr, textStatus, errorThrown) {
			// Handle error here
			console.error('Error:', errorThrown);
		}
	});
});


/*modify redirection on hotel search page*/
document.getElementById("search-modify").addEventListener("click",
	function() {
		window.location.href = "/us/en/hotelSearch";
	});
