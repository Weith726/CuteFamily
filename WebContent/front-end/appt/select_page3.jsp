<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <jsp:useBean id="docSvc" scope="page" class="com.doc.model.DocService" />
  <jsp:useBean id="divSvc" scope="page" class="com.div.model.DivService" />
  
<!DOCTYPE html>
<html lang="en">

    <!-- Basic -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">   
   
    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
 
     <!-- Site Metas -->
    <title>OnNext - One Page Parallax Template Bootstrap 4</title>  
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Site Icons -->
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
    <link rel="apple-touch-icon" href="images/apple-touch-icon.png">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Site CSS -->
    <link rel="stylesheet" href="style.css">
    <!-- Responsive CSS -->
    <link rel="stylesheet" href="css/responsive.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/custom.css">
	<script src="js/modernizr.js"></script> <!-- Modernizr -->

    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body id="page-top" class="politics_version">

    <!-- LOADER -->
    <div id="preloader">
        <div id="main-ld">
			<div id="loader"></div>  
		</div>
    </div><!-- end loader -->
    <!-- END LOADER -->
	
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
      <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="#page-top">
		
            </li>
          </ul>
        </div>
      </div>
    </nav>
	
	<section id="home" class="main-banner parallaxie" style="background: url('uploads/banner-01.jpg')">
		<div class="heading">
		
			<h1>Cute Family </h1>			
			<h3 class="cd-headline clip is-full-width">
				<span>We care for your </span>
				<span class="cd-words-wrapper">
					<b class="is-visible">Lovely Pets</b>
					
					<b>Cats</b>
					<b>Dogs</b>
					<b>other pets</b>
				</span>
				<div class="btn-ber">
				
					<a class="get_btn hvr-bounce-to-top" href="#">預約門診</a>
					<table>
					<FORM id="form1" METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/appt/appt.do" name="form1">
					<input type="hidden" name="memno" value="M0004">
					<input type="hidden" name="optstate" value="0">
					<a class="learn_btn hvr-bounce-to-top" href="javascript:;" onclick="document.getElementById('form1').submit();">預約查詢</a>
        	<input type="hidden" name="action" value="listAppt_ByCompositeQuery2">
				</FORM>
				 </table>
				</div>
			</h3>
			
		</div>
		
	</section>

<!--     <div id="about" class="section wb"> -->
<!--         <div class="container"> -->
<!--             <div class="row"> -->
<!--                 <div class="col-md-6"> -->
<!--                     <div class="message-box">                         -->
<!--                         <h2>看診進度查詢</h2> -->
<!--                         <p> Integer rutrum ligula eu dignissim laoreet. Pellentesque venenatis nibh sed tellus faucibus bibendum. Sed fermentum est vitae rhoncus molestie. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed vitae rutrum neque. Ut id erat sit amet libero bibendum aliquam. Donec ac egestas libero, eu bibendum risus. Phasellus et congue justo. </p> -->
<!-- 						<p>Sed vitae rutrum neque. Ut id erat sit amet libero bibendum aliquam. Donec ac egestas libero, eu bibendum risus. Phasellus et congue justo.</p> -->

<!--                         <a href="#" class="sim-btn hvr-bounce-to-top"><span>Contact Us</span></a> -->
<!--                     </div>end messagebox -->
<!--                 </div>end col -->

<!--                 <div class="col-md-6"> -->
<!--                     <div class="right-box-pro wow fadeIn"> -->
<!--                         <img src="uploads/about_04.jpg" alt="" class="img-fluid img-rounded"> -->
<!--                     </div>end media -->
<!--                 </div>end col -->
<!--             </div>end row -->
<!--         </div>end container -->
<!--     </div>end section -->
	
    <div id="services" class="section lb">
        <div class="container">
            <div class="section-title text-center">
                <h3>Services</h3>
                <p>Quisque eget nisl id nulla sagittis auctor quis id. Aliquam quis vehicula enim, non aliquam risus.</p>
            </div><!-- end title -->

            <div class="row">
				<div class="col-md-4">
                    <div class="services-inner-box">
						<div class="ser-icon">
							<i class="flaticon-seo"></i> <!-- icon -->
						</div>
						<iframe src="<%=request.getContextPath()%>/front-end/appt/queue.jsp" frameborder="0" width="100%" height="100%"></iframe>
					</div>
                </div><!-- end col -->
                <div class="col-md-4">
                    <div class="services-inner-box">
						<div class="ser-icon">
							<i class="flaticon-development"></i>
						</div>
						<h2>Responsive Design</h2>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
					</div>
                </div><!-- end col -->
				<div class="col-md-4">
                    <div class="services-inner-box">
						<div class="ser-icon">
							<i class="flaticon-process"></i>
						</div>
						<h2>Creative Design</h2>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
					</div>
                </div><!-- end col -->
				
            </div><!-- end row -->
        </div><!-- end container -->
    </div><!-- end section -->
	
	
	
	
	<div id="blog" class="section lb">
		<div class="container">
			<div class="section-title text-center">
                <h3>Team</h3>
                <p>優質醫師團隊給您的寵物安心照護</p>
            </div><!-- end title -->
			
			<div class="row">
				<div class="col-md-4 col-sm-6 col-lg-4">
					<div class="post-box">
						<div class="post-thumb">
							<img src="uploads/blog-01.jpg" class="img-fluid" alt="post-img" />
							<div class="date">
								<span>Select by</span>
								<span>No. / Name</span>
								
							</div>
						</div>
						<div class="post-info">
							<h4>編號／姓名查詢</h4>
							<ul>
                                <li>選擇醫師編號及姓名以查詢詳細資訊</li>
  
                               
                            </ul>
							
     
      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/doc/doc.do" >
       <b></b>
       <select size="1" name="docno" style="width:130px">
         <c:forEach var="docVO" items="${docSvc.all}" > 
          <option value="${docVO.docno}">${docVO.docno}-${docVO.docname}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-sm-6 col-lg-4">
					<div class="post-box">
						<div class="post-thumb">
							<img src="uploads/blog-02.jpg" class="img-fluid" alt="post-img" />
							<div class="date">
								<span>Select by</span>
								<span>Department</span>
							</div>
						</div>
						<div class="post-info">
							<h4>科別查詢</h4>
							<ul>
                               <li>選擇科別以查詢醫師資訊</li>
                            </ul>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/div/div.do" >
       <b><font></font></b>
       <select size="1" name="divno">
         <c:forEach var="divVO" items="${divSvc.all}" > 
          <option value="${divVO.divno}">${divVO.divname}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="listDocs_ByDivno_C">
     </FORM>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-sm-6 col-lg-4">
					<div class="post-box">
						<div class="post-thumb">
							<img src="uploads/blog-03.jpg" class="img-fluid" alt="post-img" />
							<div class="date">
								<span>All</span>
								<span>Doctors</span>
							</div>
						</div>
						<div class="post-info">
							<h4>全部醫師一覽</h4>
							<ul>
                                <li>查詢全部醫師</li>
                               
                            </ul>
							<p><a href='<%=request.getContextPath()%>/front-end/doc/listAllDoc.jsp' style="text-decoration:none;">點此瀏覽全部醫師資訊</a></p>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
	
	

    <div class="copyrights">
        <div class="container">
            <div class="footer-distributed">
<!-- 				<a href="#"><img src="images/logo.png" alt="" /></a> -->
                <div class="footer-center">
<!--                     <p class="footer-links"> -->
<!--                         <a href="#">Home</a> -->
<!--                         <a href="#">Blog</a> -->
<!--                         <a href="#">Pricing</a> -->
<!--                         <a href="#">About</a> -->
<!--                         <a href="#">Faq</a> -->
<!--                         <a href="#">Contact</a> -->
<!--                     </p> -->
                    <p class="footer-company-name">All Rights Reserved. &copy; 2020 <a href="#">CuteFamily</a>  
					
                </div>
            </div>
        </div><!-- end container -->
    </div><!-- end copyrights -->

    <a href="#" id="scroll-to-top" class="dmtop global-radius"><i class="fa fa-angle-up"></i></a>

    <!-- ALL JS FILES -->
    <script src="js/all.js"></script>
	<!-- Camera Slider -->
	<script src="js/jquery.mobile.customized.min.js"></script>
	<script src="js/jquery.easing.1.3.js"></script> 
	<script src="js/parallaxie.js"></script>
	<script src="js/headline.js"></script>
	<!-- Contact form JavaScript -->
    <script src="js/jqBootstrapValidation.js"></script>
    <script src="js/contact_me.js"></script>
    <!-- ALL PLUGINS -->
    <script src="js/custom.js"></script>
    <script src="js/jquery.vide.js"></script>

</body>
</html>