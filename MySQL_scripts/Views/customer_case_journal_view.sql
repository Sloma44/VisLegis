USE dds_main;

DROP VIEW IF EXISTS customer_case_journal;

CREATE VIEW customer_case_journal AS
	SELECT customer.id AS customer_id, customer.first_name, customer.last_name,
    law_case.case_id, law_case.case_description,
    correspondence_journal.id AS journal_id, correspondence_journal.letter_date,
    correspondence_journal.sort, correspondence_journal.internal_number,
    correspondence_journal.recipient_name, correspondence_journal.content,
    correspondence_journal.cost
    FROM customer, law_case, correspondence_journal
    WHERE law_case.customer_id = customer.id
    AND law_case.case_id = correspondence_journal.case_id;