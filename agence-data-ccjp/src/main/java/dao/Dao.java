package dao;

import java.util.List;

/**
 * Contrat que tous les DAO vont devoir respecter. Il contient les opérations
 * CRUD de base
 * 
 * @author Eric Sultan
 * @param <BO>
 *            L'objet métier générique
 * @param <PK>
 *            Le type de clé primaire générique
 */
public interface Dao<BO, PK>
{

    /**
     * Retourne un objet métier en fonction de sa clé primaire
     * 
     * @param id
     *            Clé primaire
     * @return L'objet métier trouvé
     */
    BO findById(PK id);

    /**
     * Retourne tous les objets métiers d'un type donné de la source de données
     * 
     * @return La liste des objets métiers
     */
    List<BO> findAll();

    /**
     * Crée un nouvel objet métier afin de le persister
     * 
     * @param obj
     *            L'objet à persister
     */
    void create(BO obj);

    /**
     * Retourne un objet métier mis à jour
     * 
     * @param obj
     *            L'objet à mettre à jour
     * @return L'objet métier mis à jour
     */
    BO update(BO obj);

    /**
     * Supprime un objet métier de la source de données
     * 
     * @param obj
     *            L'objet à supprimer
     */
    void delete(BO obj);
}
