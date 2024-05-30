/*auto-suggestion destination*/

$(document).ready(function(){
    $('#destination').on('input', function() {
        var query = $(this).val().trim();
        if (query.length >= 3) {
            $.ajax({
                url: '/autocomplete',
                method: 'GET',
                data: { query: query },
                success: function(data) {
                    displaySuggestions(data);
                }
            });
        } else {
            $('#suggestions').empty();
        }
    });
    
    function displaySuggestions(suggestions) {
        var suggestionList = $('#suggestions');
        suggestionList.empty();
        if (suggestions.length > 0) {
            var ul = $('<ul>').addClass('list-group');
            suggestions.forEach(function(suggestion) {
                var li = $('<li>').addClass('list-group-item').text(suggestion);
                li.on('click', function() {
                    $('#destination').val(suggestion);
                    suggestionList.empty();
                });
                ul.append(li);
            });
            suggestionList.append(ul);
        } else {
            suggestionList.text('No suggestions found');
        }
    }
});
