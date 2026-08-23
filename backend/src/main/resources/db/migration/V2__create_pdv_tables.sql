CREATE TABLE cash_registers (
    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL,
    operator_id UUID NOT NULL,
    status VARCHAR(30) NOT NULL,
    opened_at TIMESTAMP WITH TIME ZONE NOT NULL,
    closed_at TIMESTAMP WITH TIME ZONE,

    CONSTRAINT fk_cash_registers_tenant
        FOREIGN KEY (tenant_id) REFERENCES tenants(id)
);

CREATE INDEX idx_cash_registers_tenant_id
    ON cash_registers (tenant_id);

CREATE TABLE sales (
    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL,
    operator_id UUID NOT NULL,
    status VARCHAR(30) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    completed_at TIMESTAMP WITH TIME ZONE,

    CONSTRAINT fk_sales_tenant
        FOREIGN KEY (tenant_id) REFERENCES tenants(id)
);

CREATE INDEX idx_sales_tenant_id
    ON sales (tenant_id);
