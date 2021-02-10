-- 1.24
alter table article_author modify browse_count int default 0 null;

alter table article_author modify collection_count int default 0 null;

-- alter table article_author modify comments_count int default 0 null;