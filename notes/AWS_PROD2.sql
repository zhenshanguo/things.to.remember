select m.merchantid, m.ach_funding_days_eligible, m.ach_funding_days, m.app_offer_code from onbown.account_migration_info i, ihub_owner.ihub_merchant m where
m.merchantid = i.merchantid and
i.mig_type='NDACH' and 
m.ach_funding_days_eligible is null;

select merchantid, ach_funding_days_eligible, ach_funding_days, app_offer_code from ihub_owner.ihub_merchant 
where ach_funding_days_eligible is null and ach_funding_days =1;

select merchantid, faster_funding_eligible, ach_funding_days_eligible, ach_funding_days, app_offer_code from ihub_owner.ihub_merchant where merchantid in ('6247719922179396','6247719928239657','6247719923574215', '6247719927324146');
select mig_type,mig_status, count(*) from onbown.account_migration_info group by mig_type, mig_status;

select distinct m.app_offer_code from ihub_owner.ihub_merchant m, SGUMMID.TEMP_MIGRATION_VALIDATION v where 
m.merchantid = v.merchantid and v.migration_batch_id = 20191204 
and not exists (select 1 from ihub_owner.ihub_offer_map o where m.app_offer_code = o.regular_funding_offer);

update onbown.account_migration_info set mig_status = 'PENDING' where mig_type='NDACH' and 
merchantid in ('5247719900428013','5247719908681381','5247719918489536','5247719928307116','5247719969263848','5247719979068633','5247719985109413','5247719988682754');
commit;

MERGE INTO sgummid.temp_migration_validation tmv
USING 
(
  SELECT merchantid,
         app_offer_code,
         app_promo_code_v2,
         app_promo_code_start_date,
         is_pci,
         is_month_end_billing,
         faster_funding_eligible,
         ach_funding_days_eligible,
         ach_funding_days
  FROM ihub_owner.ihub_merchant m
) tm ON (tm.merchantid = tmv.merchantid and tmv.migration_batch_id=20200401)
WHEN MATCHED THEN UPDATE 
    SET tmv.app_offer_code_old = tm.app_offer_code,
    tmv.APP_PROMO_CODE_V2_old = tm.app_promo_code_v2,
    tmv.APP_PROMO_CODE_START_DATE_old = tm.app_promo_code_start_date,
    tmv.IS_PCI_old = tm.is_pci,
    tmv.IS_MONTH_END_BILLING_old = tm.is_month_end_billing,
    tmv.faster_funding_eligible_old = tm.faster_funding_eligible,
    tmv.ach_funding_eligible_old = tm.ach_funding_days_eligible,
    tmv.ach_funding_days_old = tm.ach_funding_days;
commit;    

select merchantid, app_offer_code, FASTER_FUNDING_ELIGIBLE from ihub_owner.ihub_merchant where merchantid= '123148945727919';

select m.merchantid, m.faster_funding_eligible from ihub_owner.ihub_merchant m, SGUMMID.TEMP_MIGRATION_VALIDATION v
where m.merchantid = v.merchantid and v.migration_batch_id = 20190301;

select m.merchantid, faster_funding_eligible from ihub_owner.ihub_merchant m, ONBOWN.ACCOUNT_MIGRATION_INFO i
WHERE m.MERCHANTID = i.MERCHANTID AND i.MIG_TYPE='OFFER' AND i.MIG_STATUS='SUCCESS' and m.FASTER_FUNDING_ELIGIBLE is null;

update onbown.account_migration_info set mig_type='NDCC_2' where mig_type='NDCC' and mig_status='PENDING' and merchantid in (select merchantid from ONBOWN.ACCOUNT_MIGRATION_INFO where MIG_TYPE='NDCC' and MIG_STATUS='PENDING');
commit;

select mig_type, mig_status, count(*) from ONBOWN.ACCOUNT_MIGRATION_INFO group by mig_type, mig_status;

select d.* from ihub_owner.ihub_merchant m, ihub_owner.ihub_merchant_audit_det d 
where m.id = d.ihub_merchant_id and --(d.change_detail_name='achFundingDays' or d.change_detail_name='achFundingDaysEligible') and 
m.merchantid in ('5247719907376900') order by d.hk_modified desc;

select * from SGUMMID.TEMP_MIGRATION_VALIDATION where 
    (APP_PROMO_CODE_V2 <>APP_PROMO_CODE_V2_OLD or
    APP_PROMO_CODE_START_DATE <> APP_PROMO_CODE_START_DATE_OLD or
    is_pci <> is_pci_old or
    IS_MONTH_END_BILLING <> IS_MONTH_END_BILLING_OLD or
    ACH_FUNDING_DAYS <> ACH_FUNDING_DAYS_OLD 
    --FASTER_FUNDING_ELIGIBLE <> FASTER_FUNDING_ELIGIBLE_OLD or
    --ACH_FUNDING_ELIGIBLE <> ACH_FUNDING_ELIGIBLE_OLD
    ) 
    and MIGRATION_BATCH_ID=20191106;
    
select * from SGUMMID.TEMP_MIGRATION_VALIDATION where 
    FASTER_FUNDING_ELIGIBLE is null;    

select merchantid, faster_funding_eligible, app_offer_code, ach_funding_days_eligible, ach_funding_days, app_promo_code_v2, app_promo_code_start_date 
from ihub_owner.ihub_merchant 
where merchantid in (5247719979111631, 5247711183073384, 5247719924555155, 5247719927766932, 5247719934380941, 5247719947365475, 5247719951522227, 
5247719954739114, 5247719978963271, 5247719983694143, 5247719990220080, 5247719990967979);

select id, realm_id, merchantid, ach_funding_days_eligible, ach_funding_days, faster_funding_eligible, app_offer_code from ihub_owner.ihub_merchant where merchantid in ('5247719925842222','5247719980132196','5247719952828896','5247719928134882','5247719913673217','5247719951391698','5247710003659125','5247719911525690','5247719975189482','5247719915176789','5247719977588947');

select id, realm_id, merchantid, home_locale, locale, card_account_status, check_account_status, account_open_date, hk_created from ihub_owner.ihub_merchant where realm_id in ('123146415818839', '123146415819954', '123146415823279', '123146415824799');