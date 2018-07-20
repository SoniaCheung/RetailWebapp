function submitSearch(){
	    var search_src = "find?" + document.getElementsByName("searchKeyword")[0].value;
	    var search_form = document.getElementById('searchForm');
	    search_form.action = search_src ;
}