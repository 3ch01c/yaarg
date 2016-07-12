var levelMax = 20; // Level cap

function filter(term, tableName, fieldName, show){
  //alert(term+", "+tableName+", "+fieldName+", "+show);
  var table = document.getElementById(tableName);
  var c = new Number();
  if ((c = getFieldCol(table, fieldName)) < 0){
	  alert('No "'+fieldName+'" field found in "'+tableName+'" table!');
	  return;
  };
  var element;
  for (var r = 1; r < table.rows.length; r++){
	if (getCellText(table,r,c) == term) {
	  if (!show) table.rows[r].style.display = 'none';
	  else table.rows[r].style.display = '';
	}
  }
}

function getFieldCol(table, fieldName){
  for (var c = 1; c < table.rows[0].cells.length; c++){
	  if ( getCellText(table,0,c) == fieldName) return c;
  }
  return -1;
}

function getCellText(table, row, col){
  return table.rows[row].cells[col].innerHTML.replace(/<[^>]+>/g,"")
}

function classFilter(obj, tableName, fieldName){
  if (!obj.checked) filter(obj.value, tableName, fieldName, false);
  else filter(obj.value, tableName, fieldName, true);
}

function levelFilter(filterMin, filterMax, tableName, fieldName){
  //alert("Only showing players between levels "+filterMin.value+" & "+filterMax.value+".");
  var level = new Number(1);
  var minVal = filterMin.value;
  var maxVal = filterMax.value;
  while (level < minVal){
	filter(level, tableName, fieldName, false);
	filterMax.options[level-1].disabled = true;
	level++;
  }
  while (level >= minVal && level <= maxVal){
	filter(level, tableName, fieldName, true);
	filterMin.options[level-1].disabled = false;
	filterMax.options[level-1].disabled = false;
	level++;
  }
  while (level > maxVal && level <= levelMax){
	filter(level, tableName, fieldName, false);
	filterMin.options[level-1].disabled = true;
	level++;
  }
}

// This is going to address the concurrency issues where filters using separate functions override each other.
function filterHandler(){
}