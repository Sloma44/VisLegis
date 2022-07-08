USE dds_main;

DROP VIEW IF EXISTS customer_case_court_hearing;

CREATE VIEW customer_case_court_hearing AS
	SELECT customer.id AS customer_id, customer.first_name, customer.last_name,
    law_case.case_id, law_case.signature, law_case.case_description,
    court_hearing_agenda.id AS hearing_id, court_hearing_agenda.court_hearing_date,
    court_hearing_agenda.court_hearing_hour, court_hearing_agenda.place, court_hearing_agenda.room
    FROM customer, law_case, court_hearing_agenda
    WHERE law_case.customer_id = customer.id
    AND law_case.case_id = court_hearing_agenda.case_id;