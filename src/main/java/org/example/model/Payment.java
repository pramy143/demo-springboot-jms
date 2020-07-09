package org.example.model;

import javax.validation.constraints.NotNull;

import java.util.Date;

public class Payment {

    private Long paymentId;

    @NotNull(message = "Payment type is missing.")
    private String paymentType;

    @NotNull(message = "Payment Status is missing.")
    private String paymentStatus;

    @NotNull(message = "Description can not be null")
    private String description;

    private Date createdDate;

    private Payment(Builder builder) {
        this.paymentId = builder.paymentId;
        this.description = builder.description;
        this.paymentType = builder.paymentType;
        this.paymentStatus = builder.paymentStatus;

    }

    public Payment() {

    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(final Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(final String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(final String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    public static final class Builder {
        private Long paymentId;
        private String description;
        private String paymentType;
        private String paymentStatus;

        public Builder paymentId(Long paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder paymentType(String paymentType) {
            this.paymentType = paymentType;
            return this;
        }

        public Builder paymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public Builder clone(Payment payment) {
            this.paymentId = payment.getPaymentId();
            this.paymentType = payment.getPaymentType();
            this.paymentStatus = payment.getPaymentStatus();

            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}
