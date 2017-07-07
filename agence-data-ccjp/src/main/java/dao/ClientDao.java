/**
 * 
 */
package dao;

import model.Client;

/**
 * @author ajc
 */
public interface ClientDao extends Dao<Client, Integer>
{

    public Client findById(Integer idCli);

}
