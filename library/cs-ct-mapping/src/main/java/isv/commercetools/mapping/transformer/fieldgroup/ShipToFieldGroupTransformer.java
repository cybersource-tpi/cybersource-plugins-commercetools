package isv.commercetools.mapping.transformer.fieldgroup;

import isv.commercetools.mapping.model.PaymentDetails;
import isv.payments.model.fields.ShipToFieldGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * Populates ShipToFieldGroup from cart shippingAddress
 */
public class ShipToFieldGroupTransformer implements FieldGroupTransformer<ShipToFieldGroup> {

    private final AddressFieldGroupPopulator addressFieldGroupPopulator = new AddressFieldGroupPopulator();

    @Override
    public List<ShipToFieldGroup> configure(PaymentDetails paymentDetails) {
        var fieldGroups = new ArrayList<ShipToFieldGroup>();
        var cart = paymentDetails.getCart();
        if (cart.getShippingAddress() != null) {
            var shipToFields = new ShipToFieldGroup();
            addressFieldGroupPopulator.populateFieldGroup(cart.getShippingAddress(), shipToFields);
            fieldGroups.add(shipToFields);
        }

        return fieldGroups;
    }

}
