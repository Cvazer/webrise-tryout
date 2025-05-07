create table subscriptions (
    user_id bigint not null references users(id),
    service_id text not null references services(id),
    status subscription_status not null default 'PENDING_SERVICE_CONFIRMATION',
    renew_due_date timestamp,
    primary key (user_id, service_id)
)