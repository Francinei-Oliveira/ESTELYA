CREATE TABLE tenants (
    id UUID PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    legal_name VARCHAR(200),
    document VARCHAR(30) NOT NULL,
    slug VARCHAR(100) NOT NULL,
    status VARCHAR(30) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT uk_tenants_document UNIQUE (document),
    CONSTRAINT uk_tenants_slug UNIQUE (slug)
);