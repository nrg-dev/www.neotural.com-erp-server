db.randomNumber.remove( { } )
db.randomNumber.insert({ "randomID" : 1,"number" : 10000,"code" : "E","description"    : "Employee Code"})
db.randomNumber.insert({ "randomID" : 2,"number" : 10000,"code" : "C","description"    : "Customer"})
db.randomNumber.insert({ "randomID" : 3,"number" : 10000,"code" : "V","description"    : "Vendor"})
db.randomNumber.insert({ "randomID" : 4,"number" : 10000,"code" : "P","description"    : "Product"})
db.randomNumber.insert({ "randomID" : 5,"number" : 10000,"code" : "CT","description"   : "Category"})
db.randomNumber.insert({ "randomID" : 6,"number" : 10000,"code" : "PO","description"   : "Purchase Order"})
db.randomNumber.insert({ "randomID" : 7,"number" : 10000,"code" : "SO","description"   : "Sales Order"})
db.randomNumber.insert({ "randomID" : 8,"number" : 10000,"code" : "POR","description"  : "Purchase Return"})
db.randomNumber.insert({ "randomID" : 9,"number" : 10000,"code" : "SOR","description"  : "Sales Return"})
db.randomNumber.insert({ "randomID" : 10,"number": 10000,"code" : "INVP","description" : "Purchase Invoice"})
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
db.randomNumber.insert({ "randomID" : 21,"number": 10000,"code" : "U","description" : "User"})

db.employee.remove( { } )
db.absentList.remove( { } )
db.dailyReport.remove( { } )

db.customer.remove( { } )
db.vendor.remove( { } )

db.purchaseOrder.remove( { } )
db.pOInvoice.remove( { } )
db.pOReturnDetails.remove( { } )

db.item.remove( { } )
db.discount.remove( { } )
db.units.remove( { } )
db.category.remove( { } )

db.sOInvoice.remove( { } )
db.sOInvoiceDetails.remove( { } )
db.sOReturnDetails.remove( { } )
db.salesOrder.remove( { } )

db.stock.remove( { } )

db.menu.remove( { } )

db.randomNumber.remove( {"randomID" :19 } )

db.item.remove( {"prodcode" :"P10008" } )

db.purchaseOrder.update({"_id" :ObjectId("5e84406bb184021e9421f6d3") },{$set : {"status":'Open'}})
db.transaction.find({"transactiondate" : "20/06/2020"});
db.transaction.update({"_id" :ObjectId("5efc4550f4d49fd9bc9fb4be") },{$set : {"transactiondate" : "02/07/2020"}})



db.transaction.find({"transactiondate":{ $gte: '20/06/2020', $lt: '02/07/2020' }}).pretty();


Menu Insert Query
=================
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

db.menu.insertOne({ "menucode":"MEN10008","menuname":"User Management","displayOrder":"9","langcode":"EN"});
db.menu.insertOne({ "menucode":"MEN10008","menuname":"manajemen pengguna","displayOrder":"9","langcode":"INDO"});

db.menu.insertOne({ "menucode":"MEN0","menuname":"None","displayOrder":"","langcode":"EN"});


SubMenu Insert Query
====================
db.submenu.insertOne({ "menucode":"MEN10000","submenucode":"None","submenuname":"None","displayOrder":"","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10001","submenucode":"None","submenuname":"None","displayOrder":"","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10002","submenucode":"None","submenuname":"None","displayOrder":"","langcode":"EN"});

db.submenu.insertOne({ "menucode":"MEN10003","submenucode":"SUBMEN10001","submenuname":"Orders","displayOrder":"1","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10003","submenucode":"SUBMEN10001","submenuname":"Pesanan","displayOrder":"1","langcode":"INDO"});
db.submenu.insertOne({ "menucode":"MEN10003","submenucode":"SUBMEN10002","submenuname":"Invoices","displayOrder":"2","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10003","submenucode":"SUBMEN10002","submenuname":"Faktur","displayOrder":"2","langcode":"INDO"});
db.submenu.insertOne({ "menucode":"MEN10003","submenucode":"SUBMEN10003","submenuname":"Returns","displayOrder":"3","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10003","submenucode":"SUBMEN10003","submenuname":"Kembali","displayOrder":"3","langcode":"INDO"});

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

db.submenu.insertOne({ "menucode":"MEN10006","submenucode":"None","submenuname":"None","displayOrder":"","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10007","submenucode":"None","submenuname":"None","displayOrder":"","langcode":"EN"});
db.submenu.insertOne({ "menucode":"MEN10008","submenucode":"None","submenuname":"None","displayOrder":"","langcode":"EN"});

db.submenu.insertOne({ "menucode":"0","submenucode":"None","submenuname":"None","displayOrder":"","langcode":"EN"});
