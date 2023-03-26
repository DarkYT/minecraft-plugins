package fr.dark.classes.events;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.SpectralArrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.spigotmc.event.entity.EntityDismountEvent;

import fr.dark.classes.Core;
import fr.dark.classes.mainclass.Vocations;
import fr.dark.classes.utils.Particles;
import net.minecraft.server.v1_12_R1.EnumParticle;

@SuppressWarnings("deprecation")
public class mainListener implements Listener {

	Core core;

	public mainListener(Core core) {
		this.core = core;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.setGameMode(GameMode.SURVIVAL);
		p.setLevel(0);
		p.setHealth(20D);
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = (Player) e.getPlayer();
		if (core.vocations.containsKey(p)) {
			Vocations v = core.vocations.get(p);
			if (v.getVocation().equals("Hallebardier")) {
				if (p.isSprinting()) {
					p.setSprinting(false);
					return;
				}
			}
			if (v.getVocation().equals("Executeur") || v.getVocation().equals("Medecin")) {
				Entity ent = core.getNearestEntityInSight(p, 10);
				if (ent instanceof Player) {
					Player targ = (Player) ent;
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
							"title " + p.getName() + " actionbar {\"text\":\"§6" + targ.getName() + ": §F"
									+ (int) targ.getHealth() / 2 + " §4♥\"}");
				}
			}
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	@EventHandler
	public void onPickUp(EntityPickupItemEvent e) {
		if(e.getEntity() instanceof Player){
			if(e.getItem().getType().equals(Material.ARROW)){
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (e.getBlock().hasMetadata("Breakable")) {
			e.setDropItems(false);
		}
	}

	@EventHandler
	public void onHit(EntityDamageByEntityEvent e){
		if(e.getDamager() instanceof Projectile){
			Projectile pT = (Projectile) e.getDamager();
			if(pT.getShooter() instanceof Player){
				Player shooter = (Player) pT.getShooter();
				if(core.vocations.containsKey(shooter)){
					Vocations v = core.vocations.get(shooter);
					if(v.getVocation().equals("Lancier") || v.getVocation().equals("Spadassin")){
						e.setCancelled(true);
					}
				}
			}
		}
		if (e.getEntity() instanceof Player) {
			if (e.getDamager() instanceof Player) {
				if (core.vocations.containsKey(e.getDamager())) {
					Player p = (Player) e.getDamager();
					Player t = (Player) e.getEntity();

					Vocations v = core.vocations.get(p);
					if (v.getVocation().equals("Ranger")) {
						if (p.getInventory().getItemInMainHand().getType().equals(Material.WOOD_SWORD)) {
							if (core.CardinalDirection(p) == core.CardinalDirection(t)) {
								if (core.cooldown3.containsKey(p.getName())) {

									int secondes = 15;
									long timeleft = ((core.cooldown3.get(p.getName()) / 1000) + secondes)
											- (System.currentTimeMillis() / 1000);
									if (timeleft > 0) {
										p.sendMessage(
												"§cIl reste " + timeleft + " secondes avant de ré-utiliser votre coup critique !");
										return;
									}

								}

								core.cooldown3.put(p.getName(), System.currentTimeMillis());
								t.setHealth(0.0D);
							}
						}
					}
					if (v.getVocation().equals("MaceMan")) {
						if (p.getInventory().getItemInMainHand().getType().equals(Material.IRON_SPADE)) {
							if (!(v.isMace(t))) {
								if (core.cooldown.containsKey(p.getName())) {

									int secondes = 12;
									long timeleft = ((core.cooldown.get(p.getName()) / 1000) + secondes)
											- (System.currentTimeMillis() / 1000);
									if (timeleft > 0) {
										p.sendMessage(
												"§cIl reste " + timeleft + " secondes avant d'assomer de nouveau !");
										return;
									}

								}

								core.cooldown.put(p.getName(), System.currentTimeMillis());
								v.addPlayerToMace(t);
								Particles part = new Particles(EnumParticle.FLAME, t.getLocation().add(0,2.25,0), true, 0.75F, 0.75F, 0.75F, 0, 10);
								for(Player pl : Bukkit.getOnlinePlayers()){
									part.sendPlayer(pl);
								}
								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_FIREWORK_SHOOT, 1, 5);
								t.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 1, false, false));
								t.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 60, 0, false, false));
								t.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 0, false, false));
								t.addPotionEffect(
										new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 1, false, false));
							}
						}
					}
					if (v.getVocation().equals("Executeur")) {
						if (p.getInventory().getItemInMainHand().getType().equals(Material.IRON_AXE)) {
							if (t.getHealth() <= 8) {
								t.setHealth(0.0D);
								p.getWorld().playSound(t.getLocation(), Sound.ENTITY_BLAZE_DEATH, 1, 5);
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onEffect(PlayerItemConsumeEvent e) {
		if (e.getItem().getType().equals(Material.POTION)) {
			if (e.getItem().getItemMeta().getDisplayName().equals("§8Potion Berserk")) {
				PotionMeta pM = (PotionMeta) e.getItem().getItemMeta();
				for(PotionEffect type : pM.getCustomEffects()){
					pM.removeCustomEffect(type.getType());
				}
				e.getItem().setItemMeta(pM);
				
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 300, 1, false, false));
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 1, false, false));
			}
		} else {
			return;
		}
	}

	@EventHandler
	public void onPose(BlockPlaceEvent e) {
		if (e.getBlock().getType().equals(Material.CAULDRON)) {
			if (core.vocations.containsKey(e.getPlayer())) {
				Vocations v = core.vocations.get(e.getPlayer());
				if (v.getVocation().equals("PyroArcher")) {
					e.getBlock().setMetadata("Player", new FixedMetadataValue(core, e.getPlayer().getUniqueId().toString()));
					if (e.getPlayer().hasPotionEffect(PotionEffectType.SLOW)) {
						e.getPlayer().removePotionEffect(PotionEffectType.SLOW);
					}
				}
			}
		} else if (e.getBlock().getType().equals(Material.CAKE_BLOCK)) {
			if (core.vocations.containsKey(e.getPlayer())) {
				Vocations v = core.vocations.get(e.getPlayer());
				if (v.getVocation().equals("Medecin")) {
					e.getBlock().setMetadata("Heal", new FixedMetadataValue(core, "HealingCake"));
				}
			}
		} else if (e.getBlock().getType().equals(Material.STONE_PLATE)) {
			if (core.vocations.containsKey(e.getPlayer())) {
				Vocations v = core.vocations.get(e.getPlayer());
				if (v.getVocation().equals("Ingenieur")) {
					e.getBlock().setMetadata("Hit", new FixedMetadataValue(core, "Trap"));
					e.getBlock().setMetadata("Breakable", new FixedMetadataValue(core, "True"));
				}
			}
		}
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();

		if (!(e.getClickedInventory().getName().equals("§8Choisissez votre classe"))) {
			return;
		}
		if (e.getClickedInventory().getName().equals("§8Choisissez votre classe")) {
			if (!(core.vocations.containsKey(p))) {
				switch (e.getCurrentItem().getType()) {
				case BOW:
					if (e.getCurrentItem().getItemMeta().hasEnchants()) {
						p.closeInventory();
						Vocations pA = new Vocations(core, p);
						pA.createPyroArcher();
						core.vocations.put(p, pA);
					} else {
						p.closeInventory();
						Vocations archer = new Vocations(core, p);
						archer.createArcher();
						core.vocations.put(p, archer);
					}
					break;
				case STICK:
					p.closeInventory();
					Vocations lancier = new Vocations(core, p);
					lancier.createLancier();
					core.vocations.put(p, lancier);
					break;
				case IRON_SWORD:
					p.closeInventory();
					Vocations hM = new Vocations(core, p);
					hM.createHandMan();
					core.vocations.put(p, hM);
					break;
				case POTION:
					p.closeInventory();
					Vocations sK = new Vocations(core, p);
					sK.createSkirmisher();
					core.vocations.put(p, sK);
					break;
				case SHIELD:
					p.closeInventory();
					Vocations sT = new Vocations(core, p);
					sT.createSentinelle();
					core.vocations.put(p, sT);
					break;
				case IRON_AXE:
					if (e.getCurrentItem().getItemMeta().hasEnchants()) {
						p.closeInventory();
						Vocations Hall = new Vocations(core, p);
						Hall.createHallebardier();
						core.vocations.put(p, Hall);
					} else {
						p.closeInventory();
						Vocations Exe = new Vocations(core, p);
						Exe.createExecuteur();
						core.vocations.put(p, Exe);
					}
					break;
				case WOOD_SWORD:
					p.closeInventory();
					Vocations r = new Vocations(core, p);
					r.createRanger();
					core.vocations.put(p, r);
					break;
				case IRON_SPADE:
					p.closeInventory();
					Vocations mm = new Vocations(core, p);
					mm.createMaceM();
					core.vocations.put(p, mm);
					break;
				case PAPER:
					p.closeInventory();
					Vocations med = new Vocations(core, p);
					med.createMedecin();
					core.vocations.put(p, med);
					break;
				case APPLE:
					p.closeInventory();
					Vocations brute = new Vocations(core, p);
					brute.createBrute();
					core.vocations.put(p, brute);
					break;
				case STONE_PLATE:
					p.closeInventory();
					Vocations ing = new Vocations(core, p);
					ing.createIngenieur();
					core.vocations.put(p, ing);
					break;
				case ARROW:
					p.closeInventory();
					Vocations arb = new Vocations(core, p);
					arb.createArbaletrier();
					core.vocations.put(p, arb);
					break;
				case SADDLE:
					p.closeInventory();
					Vocations cav = new Vocations(core, p);
					cav.createCavalier();
					core.vocations.put(p, cav);
					break;
				case BONE:
					p.closeInventory();
					Vocations dog = new Vocations(core, p);
					dog.createChien();
					core.vocations.put(p, dog);
					break;
				case FLINT:
					p.closeInventory();
					Vocations spad = new Vocations(core, p);
					spad.createSpadassin();
					core.vocations.put(p, spad);
					break;
				default:
					break;

				}
			} else {
				e.setCancelled(true);
				p.sendMessage("§4Vous avez déjà une classe !!");
				p.closeInventory();
			}
		} else {
			return;
		}
		if (e.getCurrentItem().getType().equals(Material.ARROW)) {
			e.setCancelled(true);
		} else {
			return;
		}
		if (e.getInventory() instanceof HorseInventory) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if (e.getEntity() instanceof Horse || e.getEntity() instanceof Player) {
			e.getDrops().clear();
		}
	}

	@EventHandler
	public void onDismount(EntityDismountEvent e) {
		if (e.getDismounted() instanceof Horse) {
			if (e.getEntity() instanceof Player) {
				Horse h = (Horse) e.getDismounted();
				h.setHealth(0.0D);
			}
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (e.getClickedBlock().getType().equals(Material.CAKE_BLOCK) && e.getClickedBlock().hasMetadata("Heal")) {
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 80, 1, false, false));
			}
		}

		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
			if (!(e.hasItem())) {
				return;
			}
		}

		if (e.getAction().equals(Action.PHYSICAL)) {
			if (e.getClickedBlock().getType().equals(Material.STONE_PLATE)) {
				if (e.getClickedBlock().hasMetadata("Hit")) {
					e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HARM, 20, 0, false, false));
					if (e.getClickedBlock().hasMetadata("Breakable")) {
						Bukkit.getScheduler().scheduleSyncDelayedTask(core, new Runnable() {
							@Override
							public void run() {
								e.getClickedBlock().setType(Material.AIR);
							}
						});
					}
					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_HURT, 1, 5);
				}
			}
		}

		if (core.vocations.containsKey(p)) {
			Vocations v = core.vocations.get(p);
			if (v.getVocation().equals("PyroArcher")) {
				if (e.getClickedBlock() != null && e.getClickedBlock().getType().equals(Material.CAULDRON) && e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					if (e.getMaterial().equals(Material.ARROW)) {

						ItemStack arroww = new ItemStack(Material.SPECTRAL_ARROW, 1);
						ItemMeta arrM = arroww.getItemMeta();
						arrM.setDisplayName("§6Flèche de Feu");
						arrM.setLore(Arrays.asList(""));
						arroww.setItemMeta(arrM);

						ItemStack arr = new ItemStack(Material.SPECTRAL_ARROW, 5);
						ItemMeta arrrM = arr.getItemMeta();
						arrrM.setDisplayName("§6Flèche de Feu");
						arrrM.setLore(Arrays.asList(""));
						arr.setItemMeta(arrrM);
						if (p.getInventory().contains(arr) || v.getB()) {
							v.setTrue();
							e.setCancelled(true);
							p.sendMessage("§4Vous avez atteint votre maximum de 5 flèches !");
						} else if (!(v.getB())) {
							if (core.cooldown.containsKey(e.getPlayer().getName())) {

								int secondes = 3;
								long timeleft = ((core.cooldown.get(e.getPlayer().getName()) / 1000) + secondes)
										- (System.currentTimeMillis() / 1000);
								if (timeleft > 0) {
									
									e.getPlayer().sendMessage(
											"§cIl reste " + timeleft + " secondes avant de refaire une flèche !");
									return;
								}

							}

							core.cooldown.put(e.getPlayer().getName(), System.currentTimeMillis());
							new BukkitRunnable(){
								int tim = 3;
								@Override
								public void run() {
									if(tim == 0){
										cancel();
									}
									Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
											"title " + e.getPlayer().getName() + " actionbar {\"text\":\"§6" + tim + "§Fs\"}");
									tim--;
								}
							}.runTaskTimer(core, 0, 20);
							e.getItem().setAmount(e.getItem().getAmount() - 1);
							p.getInventory().addItem(arroww);

						}
					}
				}
				if(e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
					if(e.getClickedBlock().hasMetadata("Player")){
						if(e.getClickedBlock().getMetadata("Player").get(0).value().equals(e.getPlayer().getUniqueId().toString())){
							e.getClickedBlock().setType(Material.AIR);
							p.getInventory().addItem(new ItemStack(Material.CAULDRON_ITEM));
							p.updateInventory();
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 0, false, false));
						}
					}
				}
			}
			if (v.getVocation().equals("Lancier")) {
				if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
					if (e.getItem().getType().equals(Material.STICK)) {
						Entity ent = core.getNearestEntityInSight(p, 15);
						if (ent instanceof Player) {
							if (p.getFoodLevel() == 20) {
								Player targ = (Player) ent;
						        int[] i = {0};
						        core.shoot(p, p.getEyeLocation(), p.getEyeLocation().getDirection(), i, 20D);
								if (targ.getHealth() <= 8) {
									targ.setHealth(0.0D);
								} else {
									Player target = (Player) ent;
									target.setHealth(target.getHealth() - 8);
									target.setVelocity(targ.getVelocity().add(new Vector(0, 0.3, 0)));
								}
								p.setFoodLevel(p.getFoodLevel()-6);
								e.getItem().setAmount(e.getItem().getAmount() - 1);
							} else {
								p.sendMessage("§4Vous devez avoir votre barre de nourriture au max !");
							}
						}
					}
				}
			}
			if (v.getVocation().equals("Spadassin")) {
				if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
					if (e.getItem().getType().equals(Material.FLINT)) {
						Entity ent = core.getNearestEntityInSight(p, 15);
						if (ent instanceof Player) {
							Player targ = (Player) ent;
							if (core.cooldown.containsKey(e.getPlayer().getName())) {
								
								int secondes = 3;
								long timeleft = ((core.cooldown.get(e.getPlayer().getName()) / 1000) + secondes)
										- (System.currentTimeMillis() / 1000);
								if (timeleft > 0) {
									e.getPlayer().sendMessage("§cIl reste " + timeleft + " secondes avant de re-tirer !");
									return;
								}

							}
							core.cooldown.put(e.getPlayer().getName(), System.currentTimeMillis());
							int[] i = {0};
					        core.shoot(p, p.getEyeLocation(), p.getEyeLocation().getDirection(), i, 20D);
					        targ.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 1, false, false));
							targ.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 60, 0, false, false));
							p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1, 5);
							if (targ.getHealth() <= 3) {
								targ.setHealth(0.0D);
							} else {
								Player target = (Player) ent;
								target.setHealth(target.getHealth() - 3);
								target.setVelocity(targ.getVelocity().add(new Vector(0, 0.3, 0)));
							}
							e.getItem().setAmount(e.getItem().getAmount() - 1);
						}
					} else if (e.getItem().getType().equals(Material.GOLD_HOE)) {
						if (core.cooldown2.containsKey(e.getPlayer().getName())) {

							int secondes = 15;
							long timeleft = ((core.cooldown2.get(e.getPlayer().getName()) / 1000) + secondes)
									- (System.currentTimeMillis() / 1000);
							if (timeleft > 0) {
								e.getPlayer().sendMessage(
										"§cIl reste " + timeleft + " secondes avant de ré-utiliser votre grapin !");
								return;
							}

						}

						core.cooldown2.put(e.getPlayer().getName(), System.currentTimeMillis());
						switch (core.CardinalDirection(p)) {
						case NORTH:
							Vector v1 = p.getVelocity();
							v1.setZ(-1.2);
							v1.setY(1.5);
							p.setVelocity(v1);
							break;
						case SOUTH:
							Vector v2 = p.getVelocity();
							v2.setZ(1.2);
							v2.setY(1.5);
							p.setVelocity(v2);
							break;
						case EAST:
							Vector v3 = p.getVelocity();
							v3.setX(1.2);
							v3.setY(1.5);
							p.setVelocity(v3);
							break;
						case WEST:
							Vector v4 = p.getVelocity();
							v4.setX(-1.2);
							v4.setY(1.5);
							p.setVelocity(v4);
							break;
						default:
							break;
						}
					}
				}
			}
			if (e.getItem() != null && e.getItem().getType().equals(Material.SADDLE)) {
				if (core.vocations.containsKey(p)) {
					if (v.getVocation().equals("Cavalier")) {
						e.getItem().setAmount(0);
						ItemStack armor = new ItemStack(Material.IRON_BARDING);
						Entity ent = p.getWorld().spawnEntity(e.getClickedBlock().getLocation().add(0, 1, 0),
								EntityType.HORSE);
						Horse horse = (Horse) ent;
						horse.setAdult();
						horse.setTamed(true);
						horse.setJumpStrength(horse.getJumpStrength() * 1.3);
						horse.setMaxHealth(40D);
						horse.setHealth(40D);
						Vector vec = horse.getEyeLocation().getDirection().normalize().clone();
						horse.setVelocity(vec.clone().multiply(45D));
						horse.getInventory().setArmor(armor);
						horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
						horse.setPassenger(p);
					}
				}
			}
			if (v.getVocation().equals("Arbaletrier")) {
				if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
					if (e.getItem().getType().equals(Material.BOW)) {
						Entity ent = core.getNearestEntityInSight(p, 30);
						if (ent instanceof Player) {
							if (v.getArrowNbr() > 0) {
								Player targ = (Player) ent;
								if (targ.getHealth() <= 8) {
									targ.setHealth(0.0D);
								} else {
									Player target = (Player) ent;
									target.setHealth(target.getHealth() - 8);
									target.setVelocity(targ.getVelocity().add(new Vector(0, 0.3, 0)));
								}
								v.setArrows();
								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1, 5);
								p.getInventory().getItem(1).setAmount(v.getArrowNbr());
								p.sendMessage("§6Il vous reste " + v.getArrowNbr() + " flèches");

							} else {
								p.sendMessage("§4Vous n'avez plus de flèches !");
							}
						}
					}
				}
			}
			if (v.getVocation().equals("Medecin")) {
				if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
					if (e.getItem().getType().equals(Material.PAPER)) {
						Entity ent = core.getNearestEntityInSight(p, 5);
						if (ent instanceof Player) {
							Player targ = (Player) ent;
							Particles part = new Particles(EnumParticle.HEART, targ.getLocation().add(0,2,0), true, 0.75F, 0.75F, 0.75F, 0, 10);
							for(Player pl : Bukkit.getOnlinePlayers()){
								part.sendPlayer(pl);
							}
							targ.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 1, false, false));
						}
					}
				}
			}
			if (v.getVocation().equals("Chien")) {
				if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
					if (e.getItem().getType().equals(Material.IRON_NUGGET)) {
						Entity ent = core.getNearestEntityInSight(p, 5);
						if (ent instanceof Player) {
							Player targ = (Player) ent;
							if (core.cooldown.containsKey(e.getPlayer().getName())) {

								int secondes = 15;
								long timeleft = ((core.cooldown.get(e.getPlayer().getName()) / 1000) + secondes)
										- (System.currentTimeMillis() / 1000);
								if (timeleft > 0) {
									e.getPlayer().sendMessage(
											"§cIl reste " + timeleft + " secondes avant de re-mordre un joueur !");
									return;
								}

							}

							core.cooldown.put(e.getPlayer().getName(), System.currentTimeMillis());
							if (targ.getHealth() <= 6) {
								targ.setHealth(0.0D);
							} else {
								Player target = (Player) ent;
								target.setHealth(target.getHealth() - 6);
								target.setVelocity(targ.getVelocity().add(new Vector(0, 0.3, 0)));
							}
							targ.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 2, false, false));
							targ.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1, false, false));
							targ.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 0, false, false));
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onProjectileLaunch(EntityShootBowEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		if (core.vocations.containsKey((Player) e.getEntity())) {
			if (core.vocations.get((Player) e.getEntity()).getVocation().equals("Arbaletrier")) {
				e.setCancelled(true);
			}
		}
		if (e.getProjectile() instanceof SpectralArrow) {
			SpectralArrow arrow = (SpectralArrow) e.getProjectile();
			arrow.setGlowingTicks(0);
			arrow.setFireTicks(Integer.MAX_VALUE);
		}
		
		if(!(e.getBow().getItemMeta().hasDisplayName())){
			return;
		}

		if (e.getBow().getItemMeta().getDisplayName().equals("§6Arc Rafale")) {
			Player p = (Player) e.getEntity();
			if (core.cooldown.containsKey(p.getName())) {

				int secondes = 10;
				long timeleft = ((core.cooldown.get(p.getName()) / 1000) + secondes)
						- (System.currentTimeMillis() / 1000);
				if (timeleft > 0) {
					e.setCancelled(true);
					p.sendMessage(
							"§cIl reste " + timeleft + " secondes avant de tirer de nouveau avec cet arc !");
					return;
				}

			}

			core.cooldown.put(p.getName(), System.currentTimeMillis());
			p.getInventory().getItem(3).setAmount(p.getInventory().getItem(3).getAmount() - 3);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					e.getEntity().launchProjectile(Arrow.class);
					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1, 5);
				}

			}, 7);
			Bukkit.getScheduler().runTaskLater(core, new Runnable() {

				@Override
				public void run() {
					e.getEntity().launchProjectile(Arrow.class);
					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1, 5);
				}

			}, 14);
		}

		if (e.getBow().getItemMeta().getDisplayName().equals("§6Arc Volée")) {
			if (e.getEntity() instanceof Player) {
				Player p = (Player) e.getEntity();
				if (core.cooldown2.containsKey(p.getName())) {

					int secondes = 10;
					long timeleft = ((core.cooldown2.get(p.getName()) / 1000) + secondes)
							- (System.currentTimeMillis() / 1000);
					if (timeleft > 0) {
						e.setCancelled(true);
						p.sendMessage("§cIl reste " + timeleft + " secondes avant de tirer de nouveau avec cet arc !");
						return;
					}

				}

				core.cooldown2.put(p.getName(), System.currentTimeMillis());
				p.getInventory().getItem(3).setAmount(p.getInventory().getItem(3).getAmount() - 3);
				int[] angles = {10,-10};
				core.shoot((Player) e.getEntity(), e.getEntity().getEyeLocation(),
						e.getEntity().getEyeLocation().getDirection(), angles,e.getProjectile().getVelocity().length());
			}
		}
	}

}
