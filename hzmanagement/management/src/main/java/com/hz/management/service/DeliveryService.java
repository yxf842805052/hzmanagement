package com.hz.management.service;

import com.hz.management.entity.Delivery;
import com.hz.management.view.DataView;

public interface DeliveryService {

    DataView addDelivery(Delivery delivery);

    DataView getSpecificationList(String stock , Boolean status);

    DataView searchList(int pageNo, int pageSize, String startDate, String endDate, String query, String phone, String deposit, String type, String sort);
}
