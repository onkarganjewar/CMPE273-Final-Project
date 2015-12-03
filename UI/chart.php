
<!DOCTYPE html "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Phoenix</title>

  <!-- *** BEGIN: Styles *** -->
  <link href="Content/bootstrap.min.css" rel="stylesheet" />
  <link href="Content/Site.css" rel="stylesheet" />
  <link href="Content/pdsa-sidebar.css" rel="stylesheet" />
  <link href="Content/pdsa-summary-block.css" rel="stylesheet" />
  <link href="Content/pdsa-readme-box.css" rel="stylesheet" />
  <link href="Content/pdsa-collapser.css" rel="stylesheet" />
  <!-- *** END: Styles *** -->
</head>
<body class="notransition">
  <div class="container">
    <!-- *** BEGIN: Header Area *** -->
    <div class="row">
      <header>
        <a href="admin.html">Phoenix</a>
      </header>
    </div>
    <!-- *** END: Header Area *** -->
    <!-- *** BEGIN: Left Navigation *** -->
    <nav class="pdsa-sn-wrapper">
      <ul id="sideNavParent">
        <li>
          <a href="admin.html">
            <span class="visible-sm visible-md visible-lg">Home</span>
            <i class="glyphicon glyphicon-home visible-xs"></i>
          </a>
        </li>
        
      </ul>
    </nav>
    <!-- *** END: Left Navigation *** -->
  </div>

  <!-- *** BEGIN: Body Content *** -->
  <div class="container body-content">
    <div class="row">
      <div class="col-xs-12 col-sm-8">
        <div class="panel-group"
             id="playlists"
             data-pdsa-collapser-name="playlists"
             data-pdsa-collapser-open="glyphicon glyphicon-plus"
             data-pdsa-collapser-close="glyphicon glyphicon-minus">
              

              <?php
              $file = "proj.csv"; 

                $lines = count(file($file)); 
               
               $myfile=fopen("proj.csv","r")or die("unable to open file");

                $c = 0;
                for ($i=0; $i < $lines; $i++) { 
                  	$user = fgets($myfile);

                	$separatedUser = explode('<br>', $user);
                	$column[$i] = $separatedUser[0];
                    $c++;
                }
                for($i=0; $i<$c; $i++) {
                    $abc = explode(',', $column[$i]);
                    $pqr[$i] = $abc[1];
                }
                
                ?>


   <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'slice');
        data.addRows([
          ['instance 6377', <?php echo $pqr[1];?>],
          ['instance 6388', <?php echo $pqr[2];?>],
          ['instance 6389', <?php echo $pqr[3];?>],
          ['instance 6390', <?php echo $pqr[4];?>],
        ]);

        // Set chart options
        var options = {'title':'Keys distributed on instances',
                       'width':800,
                       'height':900,
                   'is3D': true,
               };

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
  </head>

  <body>
    <!--Div that will hold the pie chart-->
    <div id="chart_div"></div>
 

    <!--Div that will hold the pie chart-->
    <div id="chart_div"></div>


            				
              






    

    <!-- where the chart will be rendered -->
    

<div id="example5.2" style="height: 300px;width: 2000px;length:100px;"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- *** END: Body Content *** -->

  <!-- *** BEGIN: Scripts *** -->
  <script src="Scripts/jquery-1.11.0.min.js"></script>
  <script src="Scripts/bootstrap.min.js"></script>
  <script src="Scripts/pdsa-collapser.js"></script>
  <script src="Scripts/pdsa-common.js"></script>
  <!-- *** END: Scripts *** -->
</body>
</html>
