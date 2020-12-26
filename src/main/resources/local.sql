mongo localhost/admin;
use ErpDB;
db.createCollection("test")
db.createCollection("randomNumber")

db.randomNumber.remove( { } )
db.randomNumber.insert({ "randomID" : 1,"number" : 10000,"code" : "E","description"    : "Employee Code"})
db.randomNumber.insert({ "randomID" : 2,"number" : 10000,"code" : "C","description"    : "Customer"})
db.randomNumber.insert({ "randomID" : 3,"number" : 10000,"code" : "V","description"    : "Vendor"})
db.randomNumber.insert({ "randomID" : 4,"number" : 10000,"code" : "P","description"    : "Product"})
db.randomNumber.insert({ "randomID" : 5,"number" : 10000,"code" : "CT","description"   : "Category"})
db.randomNumber.insert({ "randomID" : 6,"number" : 10000,"code" : "OR","description"   : "Product Order"})
db.randomNumber.insert({ "randomID" : 7,"number" : 10000,"code" : "SO","description"   : "Sales Order"})
db.randomNumber.insert({ "randomID" : 8,"number" : 10000,"code" : "POR","description"  : "Purchase Return"})
db.randomNumber.insert({ "randomID" : 9,"number" : 10000,"code" : "SOR","description"  : "Sales Return"})
db.randomNumber.insert({ "randomID" : 10,"number": 10000,"code" : "PO","description" : "Purchase Order"})
db.randomNumber.insert({ "randomID" : 11,"number": 10000,"code" : "INVS","description" : "Sales Invoice"})
db.randomNumber.insert({ "randomID" : 12,"number": 10000,"code" : "INVPR","description": "Purchase Invoice return"})
db.randomNumber.insert({ "randomID" : 13,"number": 10000,"code" : "INVSR","description": "Sales Invoice return"})
db.randomNumber.insert({ "randomID" : 14,"number": 10000,"code" : "STD","description"  : "Stock damage"})
db.randomNumber.insert({ "randomID" : 15,"number": 10000,"code" : "D","description"    : "Discount"})
db.randomNumber.insert({ "randomID" : 16,"number": 10000,"code" : "STIN","description" : "Stock In"})
db.randomNumber.insert({ "randomID" : 17,"number": 10000,"code" : "STOT","description" : "Stock Out"})
db.randomNumber.insert({ "randomID" : 18,"number": 10000,"code" : "MEN","description": "Menu"})
db.randomNumber.insert({ "randomID" : 19,"number": 10000,"code" : "TR","description" : "Transaction"})
db.randomNumber.insert({ "randomID" : 20,"number": 10000,"code" : "PC","description" : "Petty Cash"})
db.randomNumber.insert({ "randomID" : 21,"number": 10001,"code" : "U","description" : "User"})



show collections
db.test.drop()
db.dropDatabase()
db.pOInvoiceDetails.remove( { } )
db.pOInvoiceDetails.find();
mongoexport --db ErpDB --collection test --out E:\home\test.json

sudo mongoexport --db ErpDB --collection test --out /home/ec2-user/test.json


db.employee.remove( { } )
db.absentList.remove( { } )
db.dailyReport.remove( { } )
db.contractList.remove( { } )

db.customer.remove( { } )
db.vendor.remove( { } )

db.purchaseOrder.remove( { } )
db.pOInvoice.remove( { } )
db.pOInvoiceDetails.remove( { } )
db.pOReturnDetails.remove( { } )

db.item.remove( { } )
db.discount.remove( { } )
db.units.remove( { } )
db.category.remove( { } )

db.sOInvoice.remove( { } )
db.sOInvoiceDetails.remove( { } )
db.sOReturnDetails.remove( { } )
db.salesOrder.remove( { } )

db.pettyCash.remove( { } )
db.recentUpdates.remove( { } )

db.stock.remove( { } )
db.stockDamage.remove( { } )
db.stockInDetails.remove( { } )
db.stockReturn.remove( { } )

db.menu.remove( { } )
db.submenu.remove( { } )
db.userRole.remove( { } )

db.template.remove( { } )
db.transaction.remove( { } )

db.database_sequences.remove( { } )
db.DatabaseSequence.remove( { } )
db.index.remove( { } )
db.login.remove( { } )

db.DatabaseSequence.insert({ seq: 0,"sequencename" : "order"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "recent"})
db.DatabaseSequence.insert({ seq: 1,"sequencename" : "user"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "category"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "customer"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "employee"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "dailyReport"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "absentList"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "contractList"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "pettyCash"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "item"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "discount"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "units"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "enquiry"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "career"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "poinvoice"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "poreturn"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "transaction"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "template"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "soinvoice"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "soreturn"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "sorder"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "stockdamage"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "stockReturn"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "stockIn"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "stock"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "stockOut"})
db.DatabaseSequence.insert({ seq: 0,"sequencename" : "vendor"})


db.menu.insertOne({ "menucode":"MEN10000","menuname":"Dashboard","displayOrder":"1","langcode":"EN"});
db.menu.insertOne({ "menucode":"MEN10000","menuname":"Dasbor","displayOrder":"1","langcode":"INDO"});

db.menu.insertOne({ "menucode":"MEN10001","menuname":"Employees","displayOrder":"2","langcode":"EN"});
db.menu.insertOne({ "menucode":"MEN10001","menuname":"Para karyawan","displayOrder":"2","langcode":"INDO"});

db.menu.insertOne({ "menucode":"MEN10002","menuname":"Vendors","displayOrder":"3","langcode":"EN"});
db.menu.insertOne({ "menucode":"MEN10002","menuname":"Vendor","displayOrder":"3","langcode":"INDO"});

db.menu.insertOne({ "menucode":"MEN10003","menuname":"Purchases","displayOrder":"4","langcode":"EN"});
db.menu.insertOne({ "menucode":"MEN10003","menuname":"Pembelian","displayOrder":"4","langcode":"INDO"});

db.menu.insertOne({ "menucode":"MEN10004","menuname":"Product","displayOrder":"5","langcode":"EN"});
db.menu.insertOne({ "menucode":"MEN10004","menuname":"Produk","displayOrder":"5","langcode":"INDO"});

db.menu.insertOne({ "menucode":"MEN10005","menuname":"Sales","displayOrder":"6","langcode":"EN"});
db.menu.insertOne({ "menucode":"MEN10005","menuname":"Penjualan","displayOrder":"6","langcode":"INDO"});

db.menu.insertOne({ "menucode":"MEN10006","menuname":"Stock","displayOrder":"7","langcode":"EN"});
db.menu.insertOne({ "menucode":"MEN10006","menuname":"persediaan","displayOrder":"7","langcode":"INDO"});

db.menu.insertOne({ "menucode":"MEN10007","menuname":"Finance","displayOrder":"8","langcode":"EN"});
db.menu.insertOne({ "menucode":"MEN10007","menuname":"Keuangan","displayOrder":"8","langcode":"INDO"});

db.menu.insertOne({ "menucode":"MEN10008","menuname":"Report","displayOrder":"9","langcode":"EN"});
db.menu.insertOne({ "menucode":"MEN10008","menuname":"Melaporkan","displayOrder":"9","langcode":"INDO"});

db.menu.insertOne({ "menucode":"MEN10009","menuname":"User Management","displayOrder":"9","langcode":"EN"});
db.menu.insertOne({ "menucode":"MEN10009","menuname":"manajemen pengguna","displayOrder":"10","langcode":"INDO"});

db.menu.insertOne({ "menucode":"MEN0","menuname":"None","displayOrder":"","langcode":"EN"});



db.submenu.insertOne({ "menucode":"MEN10000","submenucode":"None","submenuname":"None","displayOrder":"","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10001","submenucode":"None","submenuname":"None","displayOrder":"","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10002","submenucode":"None","submenuname":"None","displayOrder":"","langcode":"EN"});

db.submenu.insertOne({ "menucode":"MEN10003","submenucode":"SUBMEN10001","submenuname":"Orders","displayOrder":"1","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10003","submenucode":"SUBMEN10001","submenuname":"Pesanan","displayOrder":"1","langcode":"INDO"});
db.submenu.insertOne({ "menucode":"MEN10003","submenucode":"SUBMEN10002","submenuname":"Status","displayOrder":"2","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10003","submenucode":"SUBMEN10002","submenuname":"Status","displayOrder":"2","langcode":"INDO"});
db.submenu.insertOne({ "menucode":"MEN10003","submenucode":"SUBMEN10003","submenuname":"Returns","displayOrder":"3","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10003","submenucode":"SUBMEN10003","submenuname":"Kembali","displayOrder":"3","langcode":"INDO"});
db.submenu.insertOne({ "menucode":"MEN10003","submenucode":"SUBMEN10012","submenuname":"Template","displayOrder":"4","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10003","submenucode":"SUBMEN10012","submenuname":"Template","displayOrder":"4","langcode":"INDO"});

db.submenu.insertOne({ "menucode":"MEN10004","submenucode":"SUBMEN10004","submenuname":"product","displayOrder":"1","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10004","submenucode":"SUBMEN10004","submenuname":"produk","displayOrder":"1","langcode":"INDO"});
db.submenu.insertOne({ "menucode":"MEN10004","submenucode":"SUBMEN10005","submenuname":"units","displayOrder":"2","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10004","submenucode":"SUBMEN10005","submenuname":"unit","displayOrder":"2","langcode":"INDO"});
db.submenu.insertOne({ "menucode":"MEN10004","submenucode":"SUBMEN10006","submenuname":"category","displayOrder":"3","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10004","submenucode":"SUBMEN10006","submenuname":"kategori","displayOrder":"3","langcode":"INDO"});

db.submenu.insertOne({ "menucode":"MEN10005","submenucode":"SUBMEN10007","submenuname":"Orders","displayOrder":"1","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10005","submenucode":"SUBMEN10007","submenuname":"Pesanan","displayOrder":"1","langcode":"INDO"});
db.submenu.insertOne({ "menucode":"MEN10005","submenucode":"SUBMEN10008","submenuname":"Invoices","displayOrder":"2","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10005","submenucode":"SUBMEN10008","submenuname":"Faktur","displayOrder":"2","langcode":"INDO"});
db.submenu.insertOne({ "menucode":"MEN10005","submenucode":"SUBMEN10009","submenuname":"Customer","displayOrder":"3","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10005","submenucode":"SUBMEN10009","submenuname":"Pelanggan","displayOrder":"3","langcode":"INDO"});
db.submenu.insertOne({ "menucode":"MEN10005","submenucode":"SUBMEN10010","submenuname":"Returns","displayOrder":"4","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10005","submenucode":"SUBMEN10010","submenuname":"Kembali","displayOrder":"4","langcode":"INDO"});
db.submenu.insertOne({ "menucode":"MEN10005","submenucode":"SUBMEN10011","submenuname":"Promotion","displayOrder":"5","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10005","submenucode":"SUBMEN10011","submenuname":"Promosi","displayOrder":"5","langcode":"INDO"});
db.submenu.insertOne({ "menucode":"MEN10005","submenucode":"SUBMEN10013","submenuname":"Template","displayOrder":"6","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10005","submenucode":"SUBMEN10013","submenuname":"Template","displayOrder":"6","langcode":"INDO"});

db.submenu.insertOne({ "menucode":"MEN10006","submenucode":"None","submenuname":"None","displayOrder":"","langcode":"EN"});

db.submenu.insertOne({ "menucode":"MEN10007","submenucode":"SUBMEN10014","submenuname":"pettycash","displayOrder":"1","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10007","submenucode":"SUBMEN10014","submenuname":"kas kecil","displayOrder":"1","langcode":"INDO"});
db.submenu.insertOne({ "menucode":"MEN10007","submenucode":"SUBMEN10015","submenuname":"Invoices","displayOrder":"2","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10007","submenucode":"SUBMEN10015","submenuname":"Faktur","displayOrder":"2","langcode":"INDO"});
db.submenu.insertOne({ "menucode":"MEN10007","submenucode":"SUBMEN10016","submenuname":"Return","displayOrder":"3","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10007","submenucode":"SUBMEN10016","submenuname":"Kembali","displayOrder":"3","langcode":"INDO"});
db.submenu.insertOne({ "menucode":"MEN10007","submenucode":"SUBMEN10017","submenuname":"ProfitAndLoss","displayOrder":"4","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10007","submenucode":"SUBMEN10017","submenuname":"Laba rugi","displayOrder":"4","langcode":"INDO"});


db.submenu.insertOne({ "menucode":"MEN10008","submenucode":"SUBMEN10018","submenuname":"Employee Report","displayOrder":"1","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10008","submenucode":"SUBMEN10018","submenuname":"Laporan Karyawan","displayOrder":"1","langcode":"INDO"});

db.submenu.insertOne({ "menucode":"MEN10009","submenucode":"None","submenuname":"None","displayOrder":"","langcode":"EN"});

db.submenu.insertOne({ "menucode":"0","submenucode":"None","submenuname":"None","displayOrder":"","langcode":"EN"});


db.userRole.insertOne({ "_id" : NumberLong(1), "_class" : "com.erp.mongo.model.UserRole", "invnumber" : "U10000", "username" : "admin", "password" : "admin", "departmentname" : "HR", "status" : "Active", "menuItem1" : "MEN10000", "menuItem2" : "MEN10001", "menuItem3" : "MEN10002", "menuItem4" : "MEN10003", "menuItem5" : "MEN10004", "menuItem6" : "MEN10005", "menuItem7" : "MEN10006", "menuItem8" : "MEN10007", "menuItem9" : "MEN10008", "menuItem10" : "MEN10009", "purchasesubmenu1" : "SUBMEN10001", "purchasesubmenu2" : "SUBMEN10002", "purchasesubmenu3" : "SUBMEN10003", "purchasesubmenu4" : "SUBMEN10012", "productsubmenu1" : "SUBMEN10004", "productsubmenu2" : "SUBMEN10005", "productsubmenu3" : "SUBMEN10006", "salessubmenu1" : "SUBMEN10007", "salessubmenu2" : "SUBMEN10008", "salessubmenu3" : "SUBMEN10009", "salessubmenu4" : "SUBMEN10010", "salessubmenu5" : "SUBMEN10011", "salessubmenu6" : "SUBMEN10013", "financesubmenu1" : "SUBMEN10014", "financesubmenu2" : "SUBMEN10015", "financesubmenu3" : "SUBMEN10016", "financesubmenu4" : "SUBMEN10017", "reportsubmenu1" : "SUBMEN10018" });
