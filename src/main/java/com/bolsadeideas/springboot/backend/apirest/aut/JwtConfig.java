package com.bolsadeideas.springboot.backend.apirest.aut;

public class JwtConfig {
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEowIBAAKCAQEAtQuQY6wxlWDqZQfbnQZiwcxOV2R8WFSk/rKugySy2ennG/oA\r\n"
			+ "ZpPF3iYMb/TIT96Y5eBLDAMsR4OkB7Z8nMPxgcgbf+lSDWZj3wv+mNql95o1wf4T\r\n"
			+ "hX3iEN2DBaNDEIdJtqun3fvVfVHbWhTpzuMgDIzeklyOhFjjvm03Dt8FSrO37NnK\r\n"
			+ "PG9rxC1H/DiKi+42IFyLIYqulUmEJHb39wLoTdTzYCLIHfGb4qnzNeXtqcfyI86C\r\n"
			+ "apelfsvBahcQTJgQzGwXVXyDQKcZVzWoA+21Ae1SxzsjmSlg9512pT2znCgFsVvZ\r\n"
			+ "9mqNZog4NkHtmvoKG7iz+GE3v0FpdjMhhS9fZwIDAQABAoIBADOgzZcu5qDjl1Qi\r\n"
			+ "ToVT6du8KGcRl8gUs0ySpeNuFra/1hELVQvrfhSgoxFWK71jDBUZxizxxrHKH3+3\r\n"
			+ "kYee9QL+jqEGZ0i89yQHJp5uU7/tMQFWNDjwMgR/Nrrn2NsgyWuXPfK2uyXWrwfc\r\n"
			+ "SOSfrmcF0P7J+DZTpvaJ7JaIENlBNepr7MiL9kNnyS4kTVSo9KJ0dX8abZ5HJeZZ\r\n"
			+ "j7Dse/OiM0IsRH/XoP+HS5l34etEVw4FMADNbUFRM0nOq5sktJrgyVsgFMGb5ku+\r\n"
			+ "gNcDBAN0pTU+0PKV5PHOQyxEHocItDC3QiTlbkWiHEOKY8lR9XV1sLvW/40z80YE\r\n"
			+ "edkhD8ECgYEA47EpV5ugoE3VqRw9zgdBFezooRtHlxnTVzWyyTtWC93dkaLTY2WS\r\n"
			+ "ZgrHVPIKcQfjxNPX5d8yMxa63jjzN5xDFcpIselnbEb2WXvNyNu3qApysQm55SZA\r\n"
			+ "YSGl5iOMWawSvuCSNOm9p+rsse1lAm+PKEfoBBsxtYO4OwRyVUSKsqECgYEAy43B\r\n"
			+ "aqnIjRc5MIaREHkjgz6vDHQJvhEBOG6dvuWWoY/r5AdeEYEEVzWPpfduIZgYAWRX\r\n"
			+ "rKrFs4QgcKLm0Q/GToCzk7jobCq0yqfnhzIhYVNbAgm4OYMabYTb5R/+0NVeeaEH\r\n"
			+ "2UqKeKb0xIvdNytkwx/rLRG4fvvAZ6ItubKAXQcCgYEAlE/1DwTndyPcFeC4fXiK\r\n"
			+ "M77ytOZxFoKTAE6RG7UT0nz9c3ztVuR1nRROTQ2wMXojKjWXfzuIlnCq3k+YVdPq\r\n"
			+ "esod2g2f+Y6rrxQOslQ0aDgOBzW/qfzlmJ9zUdo79Lk6KK1dbywGBtfmv+tTjWef\r\n"
			+ "h2x3PB1+nq6jFYZK9zHDaqECgYA/SJAEcEqYisrN6lg5TeAbkqZrcbwBqlm9yK4k\r\n"
			+ "Rk6hZhRJbXzyZjEKR+2kBJvpt9ZmwGJ9nQ4f9Ij9W7f08TmO+HZcC9W+ozxX8QGL\r\n"
			+ "R6uXDYoyxQDQaJET0YVn452eIWfg2o6NIJ6SSh246V7aIxSNkWPVPDgmynAUCBi9\r\n"
			+ "LeC4iwKBgACjHVVhcTXzgFH0n13CWR7G2q+kxpC4T1t5nRH6bkr6CIf0F/HrpD02\r\n"
			+ "AwVpqimKmA1h8+XBo/qipOSuZ+2VywLa0/t1NczYtgyu4sgGuuGXUytBayxsPmLW\r\n"
			+ "J633csnHvVHxI43+9NHty+G0X509o3EODBVUI9+8ElFmWByl/bFy\r\n" + "-----END RSA PRIVATE KEY-----";

	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtQuQY6wxlWDqZQfbnQZi\r\n"
			+ "wcxOV2R8WFSk/rKugySy2ennG/oAZpPF3iYMb/TIT96Y5eBLDAMsR4OkB7Z8nMPx\r\n"
			+ "gcgbf+lSDWZj3wv+mNql95o1wf4ThX3iEN2DBaNDEIdJtqun3fvVfVHbWhTpzuMg\r\n"
			+ "DIzeklyOhFjjvm03Dt8FSrO37NnKPG9rxC1H/DiKi+42IFyLIYqulUmEJHb39wLo\r\n"
			+ "TdTzYCLIHfGb4qnzNeXtqcfyI86CapelfsvBahcQTJgQzGwXVXyDQKcZVzWoA+21\r\n"
			+ "Ae1SxzsjmSlg9512pT2znCgFsVvZ9mqNZog4NkHtmvoKG7iz+GE3v0FpdjMhhS9f\r\n" + "ZwIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";

}
