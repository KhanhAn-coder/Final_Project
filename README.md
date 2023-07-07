# Final_Project
TỔNG LIÊN ĐOÀN LAO ĐỘNG VIỆT NAM
TRƯỜNG ĐẠI HỌC TÔN ĐỨC THẮNG
KHOA CÔNG NGHỆ THÔNG TIN

 


BÁO CÁO CUỐI KỲ
MÔN PHÁT TRIỂN ỨNG DỤNG DI ĐỘNG 


BÁO CÁO CUỐI KÌ MÔN PHÁT TRIỂN ỨNG DỤNG DI ĐỘNG
 


Người hướng dẫn: TS. LÊ VĂN VANG
Người thực hiện:    HUỲNH GIA HUY – 51900798
NGUYỄN VĂN KHÁNH ÂN - 51900475
Lớp       :    19050402
Khoá     :    23






THÀNH PHỐ HỒ CHÍ MINH,  NĂM 2023

LỜI CẢM ƠN
Sau khi đã hoàn thành bài báo cáo này thì nhóm muốn gửi lời cảm ơn chân thành đến với những người đã hỗ trợ chúng em để hoàn thiện bài báo cáo này, mặc cho sự trợ giúp nhiều hay ít thì nó cũng đã góp phần lớn cho nhóm để bài báo cáo này có thể hay hơn và hoàn thiện hơn , đặc biệt là thầy Lê Văn Vang và thầy Trần Đại Nhân đã hỗ trợ em rất nhiều điều cho bài báo cáo này. Một lần nữa chúng em vô cùng cảm kích vì sự hỗ trợ của mọi người và chân thành cảm ơn mọi người.  

MỤC LỤC
Catalog
TỔNG LIÊN ĐOÀN LAO ĐỘNG VIỆT NAM	1
LỜI CẢM ƠN	2
I. Giới thiệu	4
1.1 Giới thiệu chung	4
1.2 Mục đích ý nghĩa	4
1.3 Khảo sát, so sánh các đề tài tương tự	4
1.4 Scope phạm vi đề tài.	4
II. Phân tích thiết kế	5
2.1 Use case tổng quát	5
2.2 Đặc tả	5
a. UC Đăng ký	5
b. UC Đăng nhập	6
c. UC Đăng xem chi tiết sản phẩm	7
d. UC Cập nhật sản phẩm mới	8
e. UC Xem giỏ hàng (Cart)	9
III. Cơ sở lý thuyết	10
3.1 Ứng dụng mua bán trực tuyến là gì?	10
3.2 Các ưu điểm của ứng dụng thương mại điện tử	10
3.2.1 Ưu điểm đối với người bán	10
3.2.2 Ưu điểm đối với người mua	11
3.3 Google Firebase	11
3.3.1 Google Firebase là gì?	11
3.3.2 Một số tính năng của Firebase mà đề tài sử dụng	11
3.4 RecyclerView trong Android	13
3.4.1 RecyclerView là gì?	13
3.4.2 Các thành phần tạo nên RecyclerView	13
IV. Thực nghiệm và triển khai:	14
4.1 Triển khai:	14
1. Người mua:	14
2. Người bán:	14
4.2 Triển khai cơ sở dữ liệu:	17
IV. Kết Luận	36
5.1 Đạt được	36
5.2 Chưa đạt được	37
5.3 Hướng phát triển	37
Tài liệu tham khảo	38














I. Giới thiệu

1.1 Giới thiệu chung
- Ứng dụng được chọn là một ứng dụng có khả năng mua sắm online. Đề tài được lấy ý tưởng từ các ứng dụng đặt mua hàng phổ biến tại Việt Nam như là Shopee, Tiki, Lazada, …
- Mua bán trực tuyến đang ngày càng phổ biến hiện nay, có rất nhiều người dùng từ khách hàng sử dụng ứng dụng để mua bán hoặc thậm chí các website để thực hiện giao dịch. Vì thế các doanh nghiệp, các chủ cửa hàng nhỏ lẻ, việc tạo ra cho chính mình một ứng dụng hoặc một website là việc tối quan trọng. Trong báo cáo lần này, tụi em sẽ lấy đề tài xây dựng một ứng dụng có chức năng mua bán trực tuyến. 

1.2 Mục đích ý nghĩa
- Ứng dụng mua sắm này được xây dựng để giúp khách hàng có thể mua hàng online một cách thuận tiện. App có thể được xem là một chợ Online, là trung gian kết nối giữa người mua và người bán, giúp hoạt động kinh doanh online trở nên dễ dàng hơn. Ở đó người bán đăng tải các thông tin về sản phẩm và dịch vụ mà không cần người tư vấn hay vận chuyển, đồng thời người mua cũng tiếp cận được các thông tin ấy một cách trực quan mà không cần đến cửa hàng. 

1.3 Khảo sát, so sánh các đề tài tương tự
- Các ứng dụng tương tự đã được khảo sát bao gồm các app đặt hàng trực tuyến. Trọng tâm là app Shopee, ngoài ra còn hai ứng dụng mang tính tham khảo là Tiki và Lazada.
- So với các ứng dụng được khảo sát thì ứng dụng do tụi em xây dựng còn khá nhiều hạn chế, ví dụ như là app shopee có thể xem là trung gian giữa người mua và người bán. Thì ứng dụng được tụi em xây dựng không có chủ thể người bán, việc theo dõi đơn hàng và thêm sản phẩm vào danh sách đều do admin (chính người tạo app quản lí).

1.4 Scope phạm vi đề tài.
- Mục tiêu của đề tài: Xây dựng được một ứng dụng có các chức năng cơ bản của việc mua bán hàng online.
- Các chức năng cơ bản của đề tài:
	Người mua (Khách hàng):
	Có thể đăng ký tài khoản, sử dụng tài khoản để đăng nhập vào ứng dụng.
	Xem các danh mục sản phẩm được trình bày trong ứng dụng
	Xem chi tiết thông tin về sản phẩm đó
	Xem thông tin chi tiết về các sản phẩm đã được thêm vào giỏ hàng, các thông tin phụ bao gồm số lượng vủa sản phẩm vừa thêm vào, tổng giá trị của đơn hàng đó.
	Cập nhật thông tin cá nhân
	Quản trị viên (Admin):
	Cập nhật thông tin về giỏ hàng của người dùng
	Thêm sản phẩm vào ứng dụng
	Tài khoản admin được đăng nhập riêng
- Cấu trúc phân chia công việc: Ứng dụng được xây dựng bởi hai thành viên, mỗi thành viên sẽ đóng góp đều vào việc xây dựng app và viết báo cáo cho ứng dụng này.

II. Phân tích thiết kế
2.1 Use case tổng quát
 
2.2 Đặc tả
a. UC Đăng ký
 
Use Case	Đăng ký
Scenario	Khách hàng muốn sử dụng ứng dụng mà chưa có tài khoản
Triggering Event:	Khách hàng muốn đăng ký
Actors:	Khách hàng
Flow of events:	Actor	System
	1. Khách hàng nhập thông tin cá nhân

2. Bấm đăng ký	1.1 Hệ thống sẽ kiểm tra thông tin.
1.2 Hệ thống sẽ thông báo cho khách hàng biết rằng đăng ký thành công hay không thành công
Exception Conditions	3.2 Nếu số điện thoại đã được đăng ký trong database thì khác hàng sẽ không thể dùng số điện thoại đã đăng ký đê đăng ký tiếp

b. UC Đăng nhập

 
Use Case	Đăng nhập
Scenario	Khách hàng đăng nhập vào ứng dụng để sử dụng dich vụ
Triggering Event:	Khách hàng mở ứng dụng
Actors:	Khách hàng
Flow of events:	Actor	System
	1. Khách hàng nhập thông tin cá nhân

2. Bấm đăng nhập	1.2 Hệ thống sẽ kiểm tra thông tin.
1.2 Hệ thống sẽ thông báo cho khách hàng biết rằng thông tin đã nhập đúng hay không.
Exception Conditions	Báo lỗi nếu khách hàng nhập sai thông tin tài khoản hoặc mật khẩu.

c. UC Đăng xem chi tiết sản phẩm

 
Use Case	Xem chi tiết sản phẩm
Scenario	Khách hàng xem chi tiết về một sản phẩm
Triggering Event:	Khách hàng bấm vào một sản phẩm
Actors:	Khách hàng
Flow of events:	Actor	System
	1. Khách hàng bấm vào một sản phẩm

	1.3 Hệ thống trả về thông tin chi tiết sản phẩm, bao gồm thông tin, giá sản phẩm, tên sản phẩm

Exception Conditions	

d. UC Cập nhật sản phẩm mới

 
Use Case	Cập nhật sản phẩm mới
Scenario	Admin thêm sản phẩm vào database
Triggering Event:	Admin thêm sản phẩm
Actors:	Admin, hệ thống
Flow of events:	Actor	System
	1. Admin điền thông tin sản phẩm mới
2. Xác nhận thành công	1.1 Hệ thống tiếp nhận thông tin của sản phẩm, bao gồm tên sản phẩm, mô tả sản phẩm, giá sản phẩm
1.2 Thêm vào database sau khi admin xác nhận
Exception Conditions	


e. UC Xem giỏ hàng (Cart)

 
Use Case	Xem và cập nhật giỏ hàng
Scenario	Người dùng muốn kiểm tra các sản phẩm đã được thêm vào giỏ hàng
Triggering Event:	Người dùng bấm vào giỏ hàng
Actors:	Khách hàng
Flow of events:	Actor	System
	1. Người dùng bấm vào xem giỏ hàng
2. Nếu người dùng có nhu cầu thêm xóa sản phẩm hoặc chỉnh sửa lại số lượng của sản phẩm	1.1 Hệ thống trả về kết quả giỏ hàng của người dùng.
1.2 Hiển thị chi tiết các sản phẩm đã được thêm vào, tính tổng giá trị của giỏ hàng
2.1 Hệ thống ghi nhận thay đổi của người dùng
2.2 Hệ thống cập nhật lại thông tin trên database, sau đó trả về kết quả mới cho người dùng.
Exception Conditions	



III. Cơ sở lý thuyết
3.1 Ứng dụng mua bán trực tuyến là gì?
- Ứng dụng mua bán trực tiếp là ứng dụng cho phép doanh nghiệp và khách hàng có thể tiến hành việc mua bán sản phẩm/ dịch vụ trực tuyến. Cụ thể, khi sử dụng app này, bạn có thể xem thông tin sản phẩm, tìm kiếm các sản phẩm cần mua, đặt hàng và thanh toán nhanh chóng. 
- Có thể thấy ứng dụng mua bán trực tuyến đang hoạt động hiệu quả thay thế cho cửa hàng bán lẻ và nhân viên bán hàng. Không giới hạn về vị trí địa lý hay thời gian ngày/ đêm. Bạn có thể mua sắm bất cứ thứ gì ở bất cứ nơi đâu chỉ với vài thao tác đơn giản trên điện thoại. Đặc biệt, mua sắm trực tuyến (Online Shopping) ngày càng trở nên phổ biến. Việc mỗi doanh nghiệp tạo cho mình một ứng dụng mua bán online trở thành điều tối quan trọng.
3.2 Các ưu điểm của ứng dụng thương mại điện tử
3.2.1 Ưu điểm đối với người bán
- Sản phẩm của họ sẽ dễ dàng tiếp cận người dùng hơn.
- Tạo một kênh bán hàng rộng rãi, trực tiếp với khách hàng, đặc biệt là tốc độ nhanh và chi phí giảm được rất nhiều so với các kênh bán hàng truyền thống.
- Với ứng dụng mua bán trực tuyến, “Cửa hàng” sẽ hoạt động 24/7 một cách liên tục, chi phí thấp hơn rất nhiều do không cần nhân viên túc trực giám sát khách hàng như siêu thị bình thường, không cần bỏ tiền thuê mặt bằng buôn bán, … Tất cả đều được tự động, rất nhanh chóng và chính xác tuyệt đối.
- Tại cùng 1 thời điểm, ứng dụng có thể phục vụ hàng triệu lượt người mua hàng ở khắp nơi trên thế giới với các yêu cầu rất khác nhau về thông tin sản phẩm, chủng loại sản phẩm, giá cả, hình ảnh, chất lượng, mẫu mã,...-Thông tin, giá cả sản phẩm được cập nhật, thay đổi một cách tức thời theo sự biếnđộng của thị trường, đem lại khả năng kinh doanh mới cho doanh nghiệp:"Kinh doanh ngay cả khi bạn đang ngủ”
3.2.2 Ưu điểm đối với người mua
- Có thêm một hình thức mua hàng thuận tiện, dễ dàng, nhanh chóng
- Có thêm một hình thức thanh toán mới tiện lợi, an toàn
- Mở rộng sự chọn lựa khi mua hàng theo thị hiếu và nhu cầu
- Có cơ hội mua sản phẩm và dịch vụ trực tiếp từ nhà sản xuất hoặc nhà cung cấp chính không qua trung gian
- Người mua thực sự trở thành người chủ với toàn quyền lựa chọn sản phẩm, tìm kiếm bất kỳ thông tin nào về sản phẩm theo nhu cầu, so sánh giá cả, đặt mua hàng với hệ thống tính toán tiền tự động, đầy đủ, rõ ràng, trung thực và chính xác nhất.
3.3 Google Firebase
 3.3.1 Google Firebase là gì?
- Firebase phát triển từ Envolve, một công ty khởi nghiệp trước đó do James Tamplin và Andrew Lee thành lập vào năm 2011. Họ thành lập Firebase như một công ty vào tháng 9 - 2011. Tháng 10 năm 2014, Google chính thức mua lại Firebase. Từ đó đến nay, Firebase đã được thêm nhiều tính năng mới và được các nhà phát triển sử dụng trong việc phát triển ứng dụng của mình.
- Google Firebase là một dịch vụ cơ sở dữ liệu hoạt động trên nền tảng đám mây (Cloud). Hệ thống có tính năng chính là giúp cho người dùng có thể lập trình ứng dụng thông qua cách đơn giản hóa những thao tác với các cơ sở dữ liệu.
- Do hiện giờ Firebase là một nền tảng của Google. Chúng hoàn toàn được sử dụng miễn phí. Google Firebase có tác dụng giúp chúng ta phát triển web và các ứng dụng di động.  Tuy là miễn phí, nhưng nếu muốn nâng cấp hoặc nếu muốn xây dựng một ứng dụng lớn và sử dụng Firebase như phần back-end, chúng ta sẽ phải trả phí cho việc đó.
3.3.2 Một số tính năng của Firebase mà đề tài sử dụng
A) Firebase Realtime Database
- Đây là một cơ sở dữ liệu thời gian thực, nó được lưu trữ đám mây cho phép ta lưu trữ và đồng bộ dữ liệu. Dữ liệu được lưu trữ theo dạng cây Json và được đồng bộ theo thời gian thực đối với mọi kết nối.
- Khi xây dựng những ứng dụng đa nền tảng như Web App, tất cả client sẽ được kết nối trong cùng một cơ sở dữ liệu Firebase và sẽ được tự động cập nhật khi có thay đổi.
- Với những ưu điểm như trên, Firebase Realtime Database phù hợp với các ứng dụng cần thời gian thực như app chat hoặc là game online nhiều người chơi.
- Firebase có tính năng bảo mật hàng đầu do tất cả các dữ liệu được truyền đều được truyền qua kết nối an toàn SSL.  Các logic bảo mật dữ liệu được tập trung ở một nơi để dễ dàng cho việc sửa đổi, cập nhật.
- Cho dù client có bị mất kết nối do mạng chập chờn, mất mạng hay mạng yếu. Ứng dụng vẫn sẽ duy trì tương tác, trước khi dữ liệu được ghi đến Firebase thì tất cả dữ liệu sẽ được ghi tạm vào một cơ sở dữ liệu local. Sau khi kết nối đã được khôi phục, client sẽ nhận thấy được sự thay đổi  và đồng bộ hóa sự thay đổi đó với cơ sở dữ liệu tại Firebase. Firebase Realtime Database cho phép nhiều kết nối đồng thời mà không cần tính toán nhiều đến việc nâng cấp máy chủ. Tuy nhiên vẫn cần phải trả phí cho Firebase để nâng cấp khi quy mô của ứng dụng đủ lớn. 
- Realtim database cung cấp một bộ luật phức tạp, được gọi là Firebase Realtime Database Security Rules, dùng để xác định cách mà các dữ liệu được xây dựng và nơi mà các dữ liệu được đọc hoặc ghi. Khi sử dụng Firebase Authentication, các lập trình viên có thể xác định xem ai có quyền truy cập vào các dữ liệu nhất định và làm thế nào để họ truy cập nó.
- Có 2 loại database để lựa chọn trong Firebase:
	Cloud Firestore: Đây là database mới nhất của Firebase dành cho các app điện thoại. Được xây dựng dựa trên thành công của Realtime Database với các model dữ liệu mới. Cloud Firestore còn cung cấp nhiều tính năng, truy vấn nhanh hơn và có thể mở rộng tốt hơn Realtime Database.
	Realtime database: Là một database điển hình của Firebase. Là một database hiệu quả, độ trễ thấp cho các app điện thoại yêu cầu dữ liệu được đồng bộ hóa trong thời gian thực.
B) Firebase Remote Config
- Firebase Remote Config là một dịch vụ đám mây cho phép tạo ra thay đổi cho app mà không cần người dùng phải tải bản cập nhật về máy. Cách thức hoạt động là ta chỉ cần tạo một giá trị mặc định trong app, giá trị này dùng để kiểm soát các chức năng cũng như diện mạo của app. Sau đó ra có thể sử dụng Firebase console hoặc là Remote Config backend APIs để ghi đè lên giá trị mặc định đã tạo sẵn, các giá trị ghi đè phải được tạo và đặt cùng tên với các biến được sử dụng trong app.
- Remote Config bao gồm một thư viện dùng để xử lý các tác vụ quan trọng như đem về các giá trị, trong khi vẫn cho phép ta toàn quyền điều khiển khi các giá trị mới đã được kích hoạt, do đó nó sẽ ảnh hưởng đến trải nghiệm sử dụng app của người dùng.
- Remote Config cũng có một số chính sách và giới hạn như sau:
	Không được sử dụng Remote Config để làm một update cho app mà cần ủy quyền của người dùng. Việc này có thể khiến cho ứng dụng của bạn trở nên không được tin tưởng.
	Một Firebase project có thể có tới 2000 biến Remote Config.
	Firebase có thể chứa tới 300 phiên bản template Remote Config của bạn, mỗi template có tối đa 90 ngày cho đến khi hết hạn. 

3.4 RecyclerView trong Android
3.4.1 RecyclerView là gì?
- RecyclerView là một ViewGroup, cho phép chứa một danh sách trên một bộ sưu tập dữ liệu được cung cấp với sự hỗ trợ của ViewHolder và đưa chúng lên màn hình hiển thị của người dùng.
3.4.2 Các thành phần tạo nên RecyclerView
Các thành phần chính của RecyclerView bao gồm:
•	Adapter
•	ViewHolder
•	LayoutManager
A) Adapter
- Là một class kế thừa từ class RecyclerView.Adapter. Nó nhận vào một tập dữ liệu để hiển thị lên màn hình người dùng thông qua RecyclerView. Nó giống như một lớp chịu trách nhiệm chính để bind dữ liệu lên views và hiển thị chúng. Hầu hết các tác vụ xảy ra bên trong lớp Adapter của RecyclerView.
B) ViewHolder
- ViewHolder là một lớp kiểu helper class, giúp chúng ta vẽ UI cho từng item mà chúng ta muốn lên trên màn hình. Tất cả các tác vụ binding views của từng item diễn ra trong class này. Nó là một subclass của lớp of RecyclerView.ViewHolder.
C) LayoutManager
- LayoutManager trong RecyclerView giúp chúng ta chỉ rõ làm cách nào để chúng ta hiển thị danh sách item lên trên màn hình. Nó có thể là dạng Linear( tuyến tính) hoặc Grid(lưới). Theo mặc định, RecyclerView cung cấp một số triển khai layoutManager sẵn có. Nó giống như bộ phận quản lý của RecyclerView sẽ thông báo cho adapter của RecyclerView khi nào nên tạo view mới.



IV. Thực nghiệm và triển khai:
4.1 Triển khai:
a. Các chức năng đã triển khai trong ứng dụng:
Chức năng của ứng dụng:
1. Người mua:
- Đăng nhập: cho phép người dùng đăng nhập vào ứng dụng để lưu trữ thông tin cá nhân và lịch sử mua hàng.
-  Đăng ký: cho phép người dùng tạo tài khoản và lưu vào cơ sở dữ liệu của app
- Chỉnh sửa thông tin tài khoản: người dùng có thể thay đổi mật khẩu tài khoản.
- Có thể xem được thông tin chi tiết sản phẩm.
- Giỏ hàng: cho phép người mua thêm, xóa, chỉnh sửa số lượng sản phẩm vào giỏ hàng
- Thanh toán: tính thấy được số tiền mình phải thanh toán.

2. Người bán:
- Đăng nhập: cho phép người bán đăng nhập vào ứng dụng, tạo cửa hàng và thiết lập hồ sơ cửa hàng.
- Quản lý sản phẩm: cho phép người bán chỉnh sửa sản phẩm đăng bán và cập nhật thông tin sản phẩm của Shop..
b. Thiết kế các màn hình cho ứng dụng
- Màn hình đăng nhập tài khoản người dùng:

 

- Màn hình đăng ký tài khoản: 
 

- Màn hình trang chủ của ứng dụng:
 

- Màn hình danh sách sản phẩm:
-Màn hình chi tiết sản phẩm:
- Màn hình thông tin người dùng
-Màn hình giỏ hàng:
-Màn hình thêm xóa sản phẩm vào cửa hàng:
-Màn hình thiết lập tài khoản:

4.2 Triển khai cơ sở dữ liệu:
- Tạo cơ sở dữ liệu (Realtime Database) từ Firebase Console của google, sau đó nhúng cơ sở dữ liệu vào ứng dụng bằng cách lấy SHA-1 ke của ứng dụng đưa vào cơ sở dữ liệu.
4.2.1 Đăng ký tài khoản đưa vào cơ sở dữ liệu:
 
- Kiểm tra các trường không được bỏ trống khi nhấn nút Sign Up, nếu để trống ứng dụng sẽ tạo ra 1 thông báo yêu cầu người dùng nhập thông tin vào


- Sau khi nhấn nút Sign Up ứng dụng sẽ đi kiểm tra thông tin trên cơ sở dữ liệu để xem số điện thoại dùng để đăng ký tài khoản đã tồn tại hay chưa. Nếu chưa, ứng dụng sẽ cập nhật thông tin đăng ký của người dùng lên cơ sở dữ liệu. Nếu số đã tồn tại thì ứng dụng sẽ thông báo rằng số điện thoại đã được dùng để đăng ký tài khoản và yêu cầu người dùng nhập số điện thoại khác để đăng ký.
4.2.2. Đăng nhập tài khoản người dùng vào ứng dụng:
 



- Database sẽ tự query tới bảng thích hợp để cho phép người dùng vào.
- Đối với người dùng, đăng nhập thành công thì sẽ cho vào trang chủ. Đối với admin thì sẽ vào trang của admin. 


4.2.3 Màn hình trang chủ và danh sách sản phẩm:

 
- Tại màn hình trang chủ sau khi đăng nhập vào ta có thể thấy được danh sách các loại sản phẩm . 
- Khi ta nhấn vào các loại sản phẩm thì ứng dụng sẽ chuyển sang trang danh sách sản phẩm ứng với loại sản phẩm mà ta đã nhấn
 


- Ở đây ta chỉ cần 1 thiết kế 1 trang danh sách sản phẩm dùng chung để hiển thị danh sách các loại sản phẩm khác nhau ứng với từng danh mục sản phẩm khác nhau





4.2.4 Màn hình chi tiết sản phẩm:

- Khi ta nhấn vào mục sản phẩm trên trang ListProduct, ứng dụng sẽ chuyển sang trang chi tiết sản phẩm ProductDetails và hiển thị thông tin chi tiết của sản phẩm với dữ liệu được lấy từ Database.
-Sau khi lấy được thông tin ta tiến hành đưa những thông tin lấy được vào layout của màn hình ProductDetails
 
 



4.2.5 Màn hình giỏ hàng:
 
- Khi nhấn vào nút add thì ứng dụng sẽ chuyển sang màn hình giỏ hàng Cart và hiện lên sản phẩm mà ta đã bỏ vào giỏ hàng







 
- Tại màn hình giỏ hàng ta thêm một nút calculate để tính giá trị của tổng sản phẩm trong giỏ hàng
 

-  Khi ta nhấn vào sản phẩm thì sẽ xuất hiện một AlertDialog để cho phép người dùng có thể lựa chọn. Với “Edit” ta sẽ cho người dùng thay đổi số lượng sản phẩm trong giỏ hàng, còn với “Remove”, ta có thể xóa sản phẩm khỏi giỏ hàng

4.2.6 Màn hình đặt hàng:
 
- Màn hình đặt hàng cho yêu cầu người dùng nhập thông tin cho đơn hàng và sau khi bấm confirm thì người dùng đã đặt hàng thành công.
4.2.7. Màn hình kiểm tra đơn hàng của chủ cửa hàng:
 
- Màn hình kiểm tra đơn hàng cho phép chủ cửa hàng thấy được những đơn hàng mà người dùng đã đặt sản phẩm của shop.
- Sau khi nhấn vào nút “Show Order Products” ứng dụng sẽ chuyển sang trang chi tiết đơn hàng
 

- Trang này cho phép chủ cửa hàng thấy được những sản phẩm trong đơn hàng và tổng giá trị đơn hàng 
 
- Khi nhấn vào Status, chủ cửa hàng có thể cập nhật đơn hàng cho khách hàng tiện theo dõi
4.2.8. Màn hình đơn hàng của tôi:
 

- Sau khi các chủ cửa hàng đã xác nhận đơn hàng thì phía người dùng có thể theo dõi tình trạng đơn hàng của mình 
 

- Sau khi chủ shop cập nhật đơn hàng ở trạng thái “Delivered” tức là đơn hàng đã được giao tới nơi cho người dùng và người dùng có thể nhấn nút “Đã nhận được hàng”.
 
- Sau khi nhấn nút, đơn hàng sẽ bị xóa khỏi trang “MyOrders” của người dùng và đồng thời cũng sẽ bị xóa khỏi trang “Kiểm tra đơn hàng mới” của chủ cửa hàng.
 


4.2.7 Màn hình cập nhật thông tin user hiện tại
- Tại màn hình User Profile, khi ta bấm vào phần thiết lập tài khoản, hoặc biểu tượng thiết lập kế bên nút giỏ hàng, ta sẽ được giao diện như sau
 
- Các trường thông tin bao gồm tên người dùng, số điện thoại mà người dùng đăng ký, mật khẩu của tài khoản, email của tài khoản. Dữ liệu được lấy từ Real Time Database ngay khi người dùng bấm vào thiết lập tài khoản.
- Tiếp theo là sự kiện Update, sự kiện này xảy ra khi người dùng nhấn vào nút Update ở trong giao diện. Lưu ý thứ nhất, số điện thoại khi người dùng thay đổi được cập nhật sẽ không phải là số điện thoại đã được đăng kí, nó sẽ được lưu với cái tên mới là PhoneToOrder. Mật khẩu khi đổi trong đây sẽ được đổi trực tiếp trên cơ sở dữ liệu. Lần sau khi đăng nhập thì người dùng phải nhập mật khẩu mới.

- Dữ liệu được lấy về từ database hoàn toàn trùng khớp với trang Update user.





IV. Kết Luận
5.1 Đạt được
- Xây dựng được một ứng dụng có thể chạy được trên cái máy điện thoại API cao. Tương thích với nhiều kiểu điện thoại.
- Có chức năng đăng ký và đăng nhập, dữ liệu được lưu một cách tường minh, mỗi người dùng sẽ được định danh bằng số điện thoại đã đăng ký. Admin cũng tương tự
- Ứng dụng cơ bản có thể lưu thông tin người dùng, hiển thị đúng tên người dùng đã đăng nhập và đang sử dụng ứng dụng.
- Lấy được chính xác những sản phẩm được phân theo danh mục
- Lấy được chính xác những sản phẩm đã được thêm vào giỏ hàng bởi người dùng, trả về kết quả đúng với người dùng đó. Không đưa ra kết quả giỏ hàng từ người dùng khác.
- Người bán hay là Admin có hệ thống cơ sở dữ liệu riêng, có thể thông qua các nút bấm ở trang đăng nhập để chuyển chế độ đăng nhập từ người dùng phổ thông thành admin.
- Admin có chức năng thêm sản phẩm vào database, với ứng dụng của Real Time Database của Google Firebase, các cập nhật liên quan đến cơ sở dữ liệu đều được ứng dụng cập nhật gần như đồng thời.

5.2 Chưa đạt được
- Ứng dụng mới chỉ được thử nghiệm bằng các mã giả, nghĩa là các thông tin, các thông tin sản phẩm được đưa vào ứng dụng chỉ nhằm mục đích thử chức năng, ứng dụng chưa thể đưa vào sử dụng được.
- Chức năng đăng ký và đăng nhập còn vài điểm hạn chế. Đăng ký quá dễ dàng, app chỉ mới có chức năng kiểm tra các trường bị bỏ trống, chưa có chức năng xác minh người dùng. Ví dụ người dùng muốn đăng kí có thể sử dụng một số điện thoại giả, không cần đúng format 10 số. 
- Chức năng đăng nhập cũng có điểm hạn chế lớn, đó là chưa có chức năng quên mật khẩu khi người dùng lỡ quên. Người dùng mà mất mật khẩu là không có cách lấy lại được. App còn không có tính năng ghi nhớ người sử dụng. Nghĩa là mỗi lần sử dụng ứng dụng, người dùng đều phải đăng nhập lại.
- Tiếp theo là chức năng xem chi tiết sản phẩm, còn thiếu chức năng đánh giá và để lại bình luận cho sản phẩm. Người dùng chưa thể thêm đánh giá và bình luận.
- Chức năng thêm sản phẩm của admin cũng có hạn chế. Chưa thêm chức năng xóa sản phẩm trong app cho tài khoản admin. Hiện tại admin chỉ có thể thêm sản phẩm vào database chứ không thể xóa. Muốn xóa sản phẩm thì chỉ có cách là xóa trực tiếp trên Google Firebase.
- Chưa làm được chức năng theo dõi sản phẩm và tương tác giữa người mua và người bán. Chẳng hạn như chức năng nhắn tin giữa người mua và người bán. Người mua đặt hàng thành công thì không biết tình trạng đơn hàng, không biết địa điểm giao hàng và thời gian giao hàng cụ tể.

5.3 Hướng phát triển
- App vẫn còn nhiều mặt hạn chế, hướng phát triển của ứng dụng đầu tiên sẽ là giải quyết các vấn đề tồn đọng trong các chức năng đã có. Ví dụ như giải quyết vấn đề xác thực người dùng. Trong tương lai có thể thêm điều kiện là Email của người dùng phải đúng format (input người dùng nhập phải có @ chẳng hạn), bổ xung thêm phương thức xác thực bằng email, qua đó bắt buộc người dùng phải sử dụng một email thật, thuận tiện hơn cho việc quản lý.
- Đầu tiên là giải quyết các hạn chế nêu trên. Hướng phát triển lâu dài của app là phải thêm nhiều chức năng thuận tiện. Nhằm giúp người sử dụng cảm thấy thuận tiện trong việc sử dụng app. 
- Hướng phát triển tiếp theo là điều chỉnh lại giao diện app, nếu app trở nên đẹp hơn thì sẽ thu hút được người dùng hơn.

Tài liệu tham khảo
[1]	https://www.youtube.com/watch?v=fo5MEeybbnY
[2]	https://developer.android.com/?gclid=Cj0KCQjw0tKiBhC6ARIsAAOXutlk2wLnxbBfGSEkb6krMKBmcSph950jPoBZuVHJrbTTgpQRr6Ks89EaAuD4EALw_wcB&gclsrc=aw.ds
[3]	https://itnavi.com.vn/blog/firebase-la-gi
[4]	https://www.youtube.com/watch?v=Iy6K3nho2RE&list=PL3Ob3F0T-08YHxzJci2eQC47EbEZ31ka7
