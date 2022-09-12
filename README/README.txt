1. Thiết lập môi trường:
- Cài đặt java version "1.8.0_301"
- Để có thể chạy project đầu tiên phải cài đặt IDE Eclipse version: 2021-09 (4.21.0)
- Thiết lập JRE : JavaSE-11

2. Thiết lập cơ sở dữ liệu:
- Cài đặt MySQL và MySQLWorkbend.
- Tạo cơ sở dự liệu bằng file data.sql

3. Cài đặt các thư viện bên ngoài:
- Click chuột phải vào project --> Properties --> Java Build Path --> Libraries --> Add Extenals JAR --> Dẫn tới các thư viện trong thư mục libs

4. Kết nối cơ sở dữ liệu:
- Thay đổi dbName, userName, password trong class DBConnection theo cơ sở dữ liệu đã tạo trên máy.

5. Hướng dẫn sử dụng:
- Sau khi chạy hàm main trong class Main trong GUI. Ứng dụng bắt đầu với form đăng nhập. Nhập tài khoản đăng nhập
theo tài khoản của từng nhân viên, chọn quyền đăng nhập sau đó nhấn đăng nhập. Ứng dụng sẽ chuyển vào trang tương ứng
theo quyền đăng nhập đã chọn nếu nhân viên có đủ quyền. Nếu nhập sai hoặc sai quyền, ứng dụng sẽ thông báo để người dùng
đăng nhập lại.

- Phân hệ quản lí (Admin): Ở đây có các chức năng là thao tác trên các bảng dữ liệu theo cơ sở dữ liệu đã thiết kế. Các
chức năng ở các bảng hầu như giống nhau nên phần hướng dẫn sẽ lấy ví dụ ở bảng Khách Hàng.
+ View all: Cho phép load toàn bộ khách hàng lên table đã có sẳn khi có sự kiện click.
+ Khi nhấn vào 1 khách hàng bất kì trong bảng, thông tin của khách hàng đó sẽ được hiển thị lên phần Infomation.
+ Add: Sau khi nhấn Add, ứng dụng làm mới các text field hoặc reset lại các combobox để lấy dữ liệu tương ứng và cho phép 
có thể nhập dữ liệu vào trong các texfield . Người dùng có thể nhập cái thông tin cần thiết để thêm khách hàng mới.
+ Edit: Tương tự như button Add, ứng dụng cũng sẽ cho phép người dùng nhập dữ liệu vào các textfield, nhưng các textfield
ở đây không được làm mới mà vẫn hiển thị thông tin của khách hàng đang chọn. 
** Lưu ý: Phải chọn 1 khách hàng trước khi nhấn Edit 
+ Save: Lưu dữ liệu đã được nhập trên Add hoặc Edit, nếu Add thì ứng dụng sẽ thêm khách hàng mới và reset lại bảng dữ liệu
nếu Edit thì ứng dụng sẽ chỉnh sửa thông tin khách hàng đã được chọn và reset lại bảng dữ liệu.
+ Stop: Dừng thao tác Add hoặc Edit, trở lại trạng thái trước khi nhấn Add hoặc Edit.
+ Delete: Xoá 1 khách hàng được chọn ra khỏi cơ sở dữ liệu và reset lại bảng dữ liệu.
** Lưu ý: Phải chọn 1 khách hàng trước khi nhấn Delete
+ Find: Cho phép tìm kiếm các khách hàng theo các tiêu chí trong combobox như tên, số điện thoại, và nội dung tìm kiếm được
nhập trong textfield bên cạnh, sau khi nhấn Find, ứng dụng sẽ lấy các khách hàng tương ứng hiển thị trên bảng dữ liệu.

- Phân hệ nhân viên: Ở đây có các chức năng lập hoá đơn, nhập số tiền để thanh toán và lập báo cáo doanh thu. Ngoài ra có 
các chức năng khác như tìm kiểm khách hàng, tìm kiếm dịch vụ, xuất file báo cáo.
+ Lập hoá đơn: Ở đầy nhân viên nhập thông tin khách hàng ở phần Infomation, có thể chọn khách hàng có sẳn trong combobox, hoặc
nhập khách hàng mới ở các textfield, dùng View all Customer để tra cứu khách hàng nào đó. Nhấn "+" để thêm 1 dịch vụ muốn đăng
kí, có thể dùng View All Service để tra cứu dịch vụ muốn đăng kí. Có thể chỉnh sửa dịch vụ trong combobox tại ô dịch vụ và nhân 
viên phụ trách tại combobox ô nhân viên. Muốn xoá bỏ 1 dịch vụ đi thì chọn dịch vụ cần xoá và nhấn "-". Sau đó nhập số tiền khách
hàng đưa và nhấn Save để lưu hoặc nhấn Clear để reset lại toàn bộ.
+ Lập báo cáo doanh thu: Ở đây năng lập báo cáo doanh thu theo các tiêu chí khác nhau. Phần General chứa thông tin đã được thống kê
theo toàn bộ hoá đơn. Nhân viên có thể xem chi tiết của 1 hoá đơn bất kì bằng cách nhấn double click vào hoá đơn đó. Với chức năng
lập báo cáo sẽ hoạt động giống nhau với mỗi tiêu chí. Ví dụ như lập theo ngày, nhân viên chọn ngày muốn lập sau đó nhấn Create, ứng
dụng sẽ hiển thị form Báo cáo có các thông tin báo cáo, ở đây ứng dụng cung cấp thêm chức năng xuất file báo cáo, chỉ cần nhập tên file
sau đó nhấn Export ứng dụng sẽ lưu file báo cáo theo đường dẫn đã được cấp trước. Có thể chỉnh sửa đường dẫn bằng cách sửa đường dẫn
trong class FormReportDate theo đừng dẫn mong muốn.