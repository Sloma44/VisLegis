USE dds_main;

DROP VIEW IF EXISTS customer_case_income;

CREATE VIEW customer_case_income AS
	SELECT customer.id AS customer_id, customer.first_name, customer.last_name,
    law_case.case_id, law_case.case_description,
    income_for_case.id AS income_id, income_for_case.income_date,
    income_for_case.income_value, income_for_case.income_comment
    FROM customer, law_case, income_for_case
    WHERE law_case.customer_id = customer.id
    AND law_case.case_id = income_for_case.case_id;