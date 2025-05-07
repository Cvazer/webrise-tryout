create type subscription_status as enum (
    'SUBSCRIBED',
    'CANCELED',
    'WILL_NOT_RENEW',
    'PENDING_SERVICE_CONFIRMATION'
);