package org.openmrs.module.arielreports.reporting.library.queries;

/** Created by Hamilton Mutaquiha */
public class ReportQueries {

  public static final String VL_LESS_1000 =
      "select patient_id\n"
          + "from(\n"
          + "	select patient_id, max(date_test_done)\n"
          + "	from isanteplus.patient_laboratory pl\n"
          + "	where pl.test_id=856\n"
          + "	and pl.test_done=1\n"
          + "	and pl.voided<>1\n"
          + "	and pl.date_test_done between '2018-01-01' AND '2019-07-11'\n"
          + "	and pl.test_result is not null \n"
          + "	and pl.test_result>0\n"
          + "	and pl.test_result < 1000\n"
          + "	group by patient_id\n"
          + ") lab";

  public static final String VL_MORE_1000 =
      "select patient_id\n"
          + "from(\n"
          + "	select patient_id, max(date_test_done)\n"
          + "	from isanteplus.patient_laboratory pl\n"
          + "	where pl.test_id=856\n"
          + "	and pl.test_done=1\n"
          + "	and pl.voided<>1\n"
          + "	and pl.date_test_done between '2018-01-01' AND '2019-07-11'\n"
          + "	and pl.test_result is not null \n"
          + "	and pl.test_result>0\n"
          + "	and pl.test_result >= 1000\n"
          + "	group by patient_id\n"
          + ") lab "
      /*+ "and art_status.location = :location"*/ ;

  /** Mulheres Grávidas a mais de 9 meses sem data de parto */
  public static final String GRAVIDAS =
      "select * \n"
          + "from \n"
          + "(	select 	inicio.patient_id,\n"
          + "	inicio.data_inicio,\n"
          + "	ultimo.ultimo_levantamento ultimo_lev,\n"
          + "	ultimo.value_datetime proximo_lev,\n"
          + "	saida_real.encounter_datetime data_saida,\n"
          + "	pid.identifier nid,\n"
          + "	pad3.address6 localidade,\n"
          + "	pad3.address5 bairro,\n"
          + "	pad3.address3 celula,\n"
          + "	pad3.address1 ponto_referencia,\n"
          + "	pat.value as telefone,\n"
          + "	confidente.telefone as telefone_confidente,\n"
          + "	inscricao.telefone as telefone_referencia,\n"
          + "	if(saida_real.estado is not null,saida_real.estado,if(ultimo.value_datetime is not null,if(datediff(:endDate,ultimo.value_datetime)>60,'ABANDONO NAO NOTIFICADO','ACTIVO'),'SEM DATA PROXIMA NO ULTIMO LEVANTAMENTO' )) estado,\n"
          + "	concat(ifnull(pn.given_name,''),' ',ifnull(pn.middle_name,''),' ',ifnull(pn.family_name,'')) as nome_completo,\n"
          + "	pr.gender as genero,\n"
          + "	round(datediff(current_date,pr.birthdate)/365) idade_actual,\n"
          + "	if(gravida.patient_id is not null,'SIM','') gravida,\n"
          + "	proveniencia.referencia as proveniencia\n"
          + "	from		\n"
          + "	(	select patient_id,data_inicio\n"
          + "		from\n"
          + "		(	Select patient_id,min(data_inicio) data_inicio\n"
          + "			from\n"
          + "			(	Select 	p.patient_id,min(e.encounter_datetime) data_inicio\n"
          + "				from patient p \n"
          + "				inner join encounter e on p.patient_id=e.patient_id	\n"
          + "				inner join obs o on o.encounter_id=e.encounter_id\n"
          + "				where e.voided=0 and o.voided=0 and p.voided=0 \n"
          + "				and e.encounter_type in (18,6,9) \n"
          + "				and o.concept_id=1255 \n"
          + "				and o.value_coded=1256 \n"
          + "				and e.encounter_datetime<=:endDate \n"
          + "				and e.location_id=:location\n"
          + "				group by p.patient_id\n"
          + "		\n"
          + "				union\n"
          + "		\n"
          + "				Select p.patient_id,min(value_datetime) data_inicio\n"
          + "				from patient p\n"
          + "				inner join encounter e on p.patient_id=e.patient_id\n"
          + "				inner join obs o on e.encounter_id=o.encounter_id\n"
          + "				where p.voided=0 and e.voided=0 and o.voided=0 \n"
          + "				and e.encounter_type in (18,6,9) \n"
          + "				and o.concept_id=1190 \n"
          + "				and o.value_datetime is not null \n"
          + "				and o.value_datetime<=:endDate \n"
          + "				and e.location_id=:location\n"
          + "				group by p.patient_id\n"
          + "\n"
          + "				union\n"
          + "\n"
          + "				select pg.patient_id,date_enrolled data_inicio\n"
          + "				from patient p inner join patient_program pg on p.patient_id=pg.patient_id\n"
          + "				where pg.voided=0 and p.voided=0 \n"
          + "				and program_id=2 \n"
          + "				and date_enrolled<=:endDate \n"
          + "				and location_id=:location\n"
          + "				\n"
          + "				union\n"
          + "				\n"
          + "				\n"
          + "				SELECT e.patient_id, MIN(e.encounter_datetime) AS data_inicio \n"
          + "				FROM patient p\n"
          + "				inner join encounter e on p.patient_id=e.patient_id\n"
          + "				WHERE p.voided=0 AND e.voided=0 \n"
          + "				and e.encounter_type=18 \n"
          + "				and e.encounter_datetime<=:endDate \n"
          + "				and e.location_id=:location\n"
          + "				GROUP BY 	p.patient_id\n"
          + "			) inicio0\n"
          + "			group by patient_id	\n"
          + "		)inicio1\n"
          + "	)inicio\n"
          + "	inner join\n"
          + "	(\n"
          + "		select p.patient_id,o1.value_text as telefone\n"
          + "		from patient p \n"
          + "		inner join encounter e on e.patient_id=p.patient_id\n"
          + "		left join obs o1 on o1.encounter_id=e.encounter_id and o1.concept_id=1611\n"
          + "		where e.voided=0 and p.voided=0 \n"
          + "		and e.encounter_type in (5,7) \n"
          + "		and e.encounter_datetime<=:endDate \n"
          + "		and e.location_id = :location\n"
          + "\n"
          + "		union\n"
          + "\n"
          + "		select pg.patient_id, null as telefone\n"
          + "		from patient p inner join patient_program pg on p.patient_id=pg.patient_id\n"
          + "		where pg.voided=0 and p.voided=0 \n"
          + "		and program_id=1 \n"
          + "		and date_enrolled<=:endDate \n"
          + "		and location_id=:location\n"
          + "	)inscricao on inicio.patient_id=inscricao.patient_id\n"
          + "	left join\n"
          + "	(	select max_frida.patient_id,max_frida.ultimo_levantamento,o.value_datetime\n"
          + "		from\n"
          + "		(	Select p.patient_id,max(encounter_datetime) ultimo_levantamento\n"
          + "			from patient p \n"
          + "			inner join encounter e on e.patient_id=p.patient_id\n"
          + "			where p.voided=0 and e.voided=0 \n"
          + "			and e.encounter_type=18 \n"
          + "			and e.location_id=:location \n"
          + "			and e.encounter_datetime<=:endDate\n"
          + "			group by p.patient_id\n"
          + "		) max_frida \n"
          + "		left join obs o on o.person_id=max_frida.patient_id and max_frida.ultimo_levantamento=o.obs_datetime and o.voided=0 and o.concept_id=5096		\n"
          + "	) ultimo on inicio.patient_id=ultimo.patient_id\n"
          + "	left join	\n"
          + "	(	select 	pg.patient_id,ps.start_date encounter_datetime,location_id,\n"
          + "		case ps.state\n"
          + "		when 7 then 'TRANSFERIDO PARA'\n"
          + "		when 8 then 'SUSPENSO'\n"
          + "		when 9 then 'ABANDONO'\n"
          + "		when 10 then 'OBITO'\n"
          + "		else 'OUTRO' end as estado\n"
          + "		from patient p \n"
          + "		inner join patient_program pg on p.patient_id=pg.patient_id\n"
          + "		inner join patient_state ps on pg.patient_program_id=ps.patient_program_id\n"
          + "		where pg.voided=0 and ps.voided=0 and p.voided=0 \n"
          + "		and ps.start_date<=:endDate \n"
          + "		and pg.program_id=2 \n"
          + "		and ps.state in (7,8,9,10) \n"
          + "		and ps.end_date is null \n"
          + "		and location_id=:location\n"
          + "	) saida_real on inicio.patient_id=saida_real.patient_id	\n"
          + "	inner join \n"
          + "	(\n"
          + "		Select p.patient_id\n"
          + "		from patient p \n"
          + "		inner join encounter e on p.patient_id=e.patient_id\n"
          + "		inner join obs o on e.encounter_id=o.encounter_id\n"
          + "		where p.voided=0 and e.voided=0 and o.voided=0 \n"
          + "		and concept_id=1982 \n"
          + "		and value_coded=44 \n"
          + "		and e.encounter_type in (5,6) \n"
          + "		and e.encounter_datetime <= date_add(:endDate, interval -9 month)\n"
          + "		and e.location_id=:location\n"
          + "\n"
          + "		union		\n"
          + "				\n"
          + "		Select p.patient_id\n"
          + "		from patient p inner join encounter e on p.patient_id=e.patient_id\n"
          + "		inner join obs o on e.encounter_id=o.encounter_id\n"
          + "		where p.voided=0 and e.voided=0 and o.voided=0 \n"
          + "		and concept_id=1279 \n"
          + "		and e.encounter_type in (5,6) \n"
          + "		and e.encounter_datetime < date_add(:endDate, interval -9 month) \n"
          + "		and e.location_id=:location\n"
          + "\n"
          + "		union		\n"
          + "				\n"
          + "		Select p.patient_id\n"
          + "		from patient p inner join encounter e on p.patient_id=e.patient_id\n"
          + "		inner join obs o on e.encounter_id=o.encounter_id\n"
          + "		where p.voided=0 and e.voided=0 and o.voided=0 \n"
          + "		and concept_id=1600 \n"
          + "		and e.encounter_type in (5,6) \n"
          + "		and e.encounter_datetime < date_add(:endDate, interval -9 month) \n"
          + "		and e.location_id=:location		\n"
          + "				\n"
          + "		union\n"
          + "				\n"
          + "		select pp.patient_id\n"
          + "		from patient_program pp \n"
          + "		where pp.program_id=8 and pp.voided=0 \n"
          + "		and pp.date_enrolled < date_add(:endDate, interval -9 month) \n"
          + "		and pp.location_id=:location\n"
          + "	\n"
          + "	) gravida on gravida.patient_id= inicio.patient_id\n"
          + "	left join\n"
          + "	(\n"
          + "		select p.patient_id, pr.name referencia, max(pp.date_enrolled) as data_inicio_programa\n"
          + "		from patient p\n"
          + "		inner join patient_program pp on pp.patient_id=p.patient_id\n"
          + "		inner join program pr on pr.program_id=pp.program_id\n"
          + "		where pp.date_enrolled < :endDate\n"
          + "		and pp.date_completed is null\n"
          + "		and pp.location_id=:location\n"
          + "		and p.voided=0 and pp.voided=0\n"
          + "		group by patient_id,pr.name\n"
          + "	) proveniencia on proveniencia.patient_id=inicio.patient_id\n"
          + "	left join \n"
          + "	(	select pad1.*\n"
          + "		from person_address pad1\n"
          + "		inner join \n"
          + "		(\n"
          + "			select person_id,min(person_address_id) id \n"
          + "			from person_address\n"
          + "			where voided=0\n"
          + "			group by person_id\n"
          + "		) pad2\n"
          + "		where pad1.person_id=pad2.person_id and pad1.person_address_id=pad2.id\n"
          + "	) pad3 on pad3.person_id=inicio.patient_id				\n"
          + "	left join 			\n"
          + "	(	select pn1.*\n"
          + "		from person_name pn1\n"
          + "		inner join \n"
          + "		(\n"
          + "			select person_id,min(person_name_id) id \n"
          + "			from person_name\n"
          + "			where voided=0\n"
          + "			group by person_id\n"
          + "		) pn2\n"
          + "		where pn1.person_id=pn2.person_id and pn1.person_name_id=pn2.id\n"
          + "	) pn on pn.person_id=inicio.patient_id			\n"
          + "	left join\n"
          + "	(   select pid1.*\n"
          + "		from patient_identifier pid1\n"
          + "		inner join\n"
          + "		(\n"
          + "			select patient_id,min(patient_identifier_id) id\n"
          + "			from patient_identifier\n"
          + "			where voided=0\n"
          + "			group by patient_id\n"
          + "		) pid2\n"
          + "		where pid1.patient_id=pid2.patient_id and pid1.patient_identifier_id=pid2.id\n"
          + "	) pid on pid.patient_id=inicio.patient_id	\n"
          + "	left join person pr on pr.person_id=inicio.patient_id\n"
          + "	left join person_attribute pat on pat.person_id=inicio.patient_id and pat.person_attribute_type_id=9 and pat.value is not null and pat.value<>'' and pat.voided=0\n"
          + "	left join\n"
          + "	(\n"
          + "		select p.patient_id, o.value_text as telefone\n"
          + "		from patient p\n"
          + "		inner join encounter e on e.patient_id=p.patient_id\n"
          + "		inner join obs o on o.encounter_id=e.encounter_id \n"
          + "		where p.voided=0 and e.voided=0 and o.voided=0\n"
          + "		and e.encounter_type=34\n"
          + "		and o.concept_id=1611\n"
          + "		and e.location_id=:location\n"
          + "	) confidente on inicio.patient_id=confidente.patient_id\n"
          + ") coortes\n"
          + "group by patient_id";

  /** Pacientes com CV < 0 */
  public static final String PACIENTES_CV_NEGATIVA =
      "Select p.patient_id,\n"
          + "	pid.identifier as nid, \n"
          + "	concat(ifnull(pn.given_name,''),' ',ifnull(pn.middle_name,''),' ',ifnull(pn.family_name,'')) as nome_completo,\n"
          + "	o.value_numeric valor_cv,\n"
          + "	o.obs_datetime data_cv\n"
          + "from patient p\n"
          + "inner join encounter e on p.patient_id=e.patient_id\n"
          + "inner join obs o on e.encounter_id=o.encounter_id				\n"
          + "left join 			\n"
          + "(	select pn1.*\n"
          + "	from person_name pn1\n"
          + "	inner join \n"
          + "	(\n"
          + "		select person_id,min(person_name_id) id \n"
          + "		from person_name\n"
          + "		where voided=0\n"
          + "		group by person_id\n"
          + "	) pn2\n"
          + "	where pn1.person_id=pn2.person_id and pn1.person_name_id=pn2.id\n"
          + ") pn on pn.person_id=p.patient_id			\n"
          + "left join\n"
          + "(   select pid1.*\n"
          + "	from patient_identifier pid1\n"
          + "	inner join\n"
          + "	(\n"
          + "		select patient_id,min(patient_identifier_id) id\n"
          + "		from patient_identifier\n"
          + "		where voided=0\n"
          + "		group by patient_id\n"
          + "	) pid2\n"
          + "	where pid1.patient_id=pid2.patient_id and pid1.patient_identifier_id=pid2.id\n"
          + ") pid on pid.patient_id=p.patient_id\n"
          + "where p.voided=0 and e.voided=0 and o.voided=0 \n"
          + "and e.encounter_type in (13,6,9) \n"
          + "and o.concept_id=856 \n"
          + "and o.value_numeric is not null\n"
          + "and o.value_numeric < 0\n"
          + "and e.encounter_datetime < :endDate \n"
          + "and e.location_id=:location";
}
